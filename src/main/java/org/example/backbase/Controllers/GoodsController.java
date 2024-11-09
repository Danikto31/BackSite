package org.example.backbase.Controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backbase.Entity.Goods;
import org.example.backbase.Services.CookieService;
import org.example.backbase.Services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    private final Set<String> DEFAULT_CATEGORIES = new HashSet<>(List.of("popular"));

    @Autowired
    private CookieService cookieService;

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/saveGood")
    public void saveGood(HttpServletRequest request, HttpServletResponse response) {
        long sellerId;
        try {
            sellerId = cookieService.getCookieClientByCookie(
                    Arrays.stream(request.getCookies())
                            .filter(c -> c.getName().equals(CookieController.getCookieName()))
                            .findFirst().orElseThrow()
            ).getId();
        } catch (NoSuchElementException e) {
            System.out.println("User is not logged in");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        long goodId = goodsService.saveGood(
                (String) request.getAttribute("title"),
                sellerId,
                (int) request.getAttribute("cost"),
                (int) request.getAttribute("count"),
                (String) request.getAttribute("description"),
                (String) request.getAttribute("categories")
        );
        try (Writer writer = response.getWriter()) {
            if (goodId < 0) throw new NoSuchElementException("The good wasn't saved");
            writer.write("goodId: " + goodId);
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping("/search")
    public void search(HttpServletRequest request, HttpServletResponse response) {
        String title = (String) request.getAttribute("title");
        Set<String> categories = Arrays.stream(Optional.ofNullable((String) request.getAttribute("categories")).orElse("").replaceAll("[\\[\\] ]", "").split(",")).collect(Collectors.toSet());
        try (Writer writer = response.getWriter()) {
            ObjectMapper mapper = new ObjectMapper();
            List<Goods> searchResult;
            if (title != null && !categories.isEmpty()) {
                searchResult = goodsService.findByTitleAndCategories(title, categories);
            } else if (title != null) {
                searchResult = goodsService.findByTitle(title);
            } else if (!categories.isEmpty()) {
                searchResult = goodsService.findByCategories(categories);
            } else {
                searchResult = goodsService.findByCategories(DEFAULT_CATEGORIES);
            }
            searchResult.forEach(good -> {
                try {
                    writer.write(mapper.writeValueAsString(good));
                } catch (IOException e) {
                    System.err.println("Cannot send good + goodId: " + good.getId() + "\n" + e.getMessage());
                }
            });
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

}
