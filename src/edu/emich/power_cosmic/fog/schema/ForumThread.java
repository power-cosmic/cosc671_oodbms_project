package edu.emich.power_cosmic.fog.schema;

import java.util.ArrayList;
import java.util.List;

public class ForumThread {
    
    private Post initialPost;
    private String title;
    
    private List<Post> posts;
    
    public ForumThread(Post initialPost, String title) {
        this.initialPost = initialPost;
        this.title = title;
        posts = new ArrayList<>();
    }
    
    public Post getInitialPost() {
        return initialPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void addPost(Post post) {
        posts.add(post);
    }
    
    public boolean removePost(Post post) {
        return posts.remove(post);
    }
    
}
