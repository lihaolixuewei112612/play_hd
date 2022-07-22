package com.example.demo.controller;

/**
 * @author LH
 * @description:
 * @date 2021-07-20 18:32
 */

import com.example.demo.pojo.Book;
import com.example.demo.pojo.Search;
import com.example.demo.result.Result;
import com.example.demo.result.ResultFactory;
import com.example.demo.service.BookService;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
//    @CrossOrigin
    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        System.out.println("----------------------------------------------------------------");
        return bookService.list();
    }
//    @CrossOrigin
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }
//    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }
    @CrossOrigin
    @PostMapping("/api/search")
    public List<Book> searchResult(@RequestBody Search s) {
        if ("".equals(s.getKeywords())) {
            return bookService.list();
        } else {
            return bookService.Search(s.getKeywords());
        }
    }
    @CrossOrigin
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") String cid) {
        System.out.println("------------------------2222222");
        System.out.println(cid);
        if (0 != Integer.valueOf(cid)) {
            return ResultFactory.buildSuccessResult(bookService.listByCategory(Integer.valueOf(cid)));
        } else {
            return ResultFactory.buildSuccessResult(bookService.list());
        }
    }

}

