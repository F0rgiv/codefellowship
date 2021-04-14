package com.jamesmansour.codefellowship.repositories;

import com.jamesmansour.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
