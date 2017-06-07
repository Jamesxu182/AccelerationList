package com.james.entity;

import javax.persistence.*;

/**
 * Created by james on 6/2/17.
 */
@Entity(name = "todo")
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
