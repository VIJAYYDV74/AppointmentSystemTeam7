package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> getLatestComments() {
        List<Comments> comments = commentsRepository.findTop5ByOrderByLocalDateTimeAsc();
        return comments;
    }
}
