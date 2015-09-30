package edu.emich.power_cosmic.fog.schema;

public class Post {

    private ForumThread forumThread;
    private FogUser poster;
    private FogUser destinationUser;
    private String content;
    private Post replyTo;
    
    public Post(FogUser poster, String content) {
        this.poster = poster;
        this.content = content;
    }
    
    public Post(FogUser poster, String content, Post replyTo) {
        this(poster, content);
        this.replyTo = replyTo;
    }
    
    public Post(FogUser poster, String content, FogUser destinationUser) {
        this(poster, content);
        this.destinationUser = destinationUser;
    }

    public ForumThread getForumThread() {
        return forumThread;
    }

    public void setForumThread(ForumThread forumThread) {
        this.forumThread = forumThread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FogUser getPoster() {
        return poster;
    }

    public FogUser getDestinationUser() {
        return destinationUser;
    }

    public Post getReplyTo() {
        return replyTo;
    }
    
}
