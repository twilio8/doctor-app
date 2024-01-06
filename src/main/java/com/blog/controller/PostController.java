package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
        @DeleteMapping("/{id}")
        public ResponseEntity<String>deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted!!",HttpStatus.OK);


    }

    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=asc
    @GetMapping
    public ResponseEntity<List<PostDto>>getAllPosts(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(name= "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
