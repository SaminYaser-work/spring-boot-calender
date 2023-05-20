package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.BlogDto;
import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/search")
    public Page<Blog> searchByTopic(
            @RequestParam(required = false) List<Integer> topics,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String content,
            @RequestParam(required = false, defaultValue = "0") String start,
            @RequestParam(required = false, defaultValue = "5") String size,
            @RequestParam(required = false, defaultValue = "id", name = "sort") String sortBy,
            @RequestParam(required = false, defaultValue = "false") String desc
    ) {

//        if (topics == null) {
//            topics = new List<Integer>();
//        }

        Sort sort = desc.equals("true") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pr = PageRequest.of(
                Integer.parseInt(start),
                Integer.parseInt(size),
                sort
        );

        return blogService.searchByTopicTitleContent(
                topics,
                title,
                content,
                pr
        );

//        return blogService.searchByTopicOrTitleOrContent(
//                topics[0],
//                title,
//                content,
//                pr
//        );
    }

//    @GetMapping
//    public List<BlogWithNamesResponse> getAllBlogsWithUsername(@RequestParam String author) {
//
//        List<Object[]> res = blogService.getAllBlogsWithUsername();
//
//        List<BlogWithNamesResponse> response = new ArrayList<>();
//        for(Object[] obj : res) {
//            BlogWithNamesResponse resp = new BlogWithNamesResponse();
//            resp.setTitle((String) obj[0]);
//            resp.setAuthor((String) obj[1]);
//            resp.setContent((String) obj[2]);
//            resp.setDate((Date) obj[3]);
//            response.add(resp);
//        }
//
//        return response;
//    }

    @GetMapping("/page/{start}/{size}/{sortBy}")
    public Page<Blog> findAll(@PathVariable int start, @PathVariable int size, @PathVariable String sortBy) {
        PageRequest pr = PageRequest.of(start, size, Sort.by(sortBy));

        return blogService.getAllBlogs(pr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int id, @RequestBody BlogDto blogDto) {
        blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int id) {
        Blog existingBlog = blogService.getBlogById(id);

        if (existingBlog != null) {
            blogService.deleteBlog(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


