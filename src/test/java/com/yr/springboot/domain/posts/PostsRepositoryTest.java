package com.yr.springboot.domain.posts;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void getPostsSaved() {

        String title = "테스트 타이틀";
        String content = "테스트 내용";

        postRepository.save(Posts.builder().title(title).content(content).author("choi").build());

        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        
    }

    @Test
    public void saveBaseTimeEntity () {

        LocalDateTime now = LocalDateTime.of(2021, 4, 11, 0, 0, 0);
        postRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}
