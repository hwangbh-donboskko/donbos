package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {

    @Id@GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MyComment> commentSet = new HashSet<>();

    public void setMyComment(MyComment myComment){

        this.getCommentSet().add(myComment);
        myComment.setPost(this);

    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                '}';
    }
}
