package com.smp.cutecatcontest;

/**
 * Created by Steve on 11/11/14.
 */
public class CatData
{
    private String imageUrl;
    private String author;
    private String votes;

    public CatData() {}

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getVotes()
    {
        return votes;
    }

    public void setVotes(String votes)
    {
        this.votes = votes;
    }
}
