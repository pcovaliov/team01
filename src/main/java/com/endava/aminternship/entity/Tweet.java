package com.endava.aminternship.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.endava.aminternship.util.JsonDateSerializer;


@Entity
@Table(name = "tweet_table")
public class Tweet {
	public Tweet(){
		
	};
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweet=" + tweet + ", date=" + date
				+ ", user=" + user + "]";
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "tweet")
	@Size(min = 1, max = 140, message = "Invalid number of characters")
	private String tweet;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="post_date")
	private Date date = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_ID", nullable = false)
	private User user;
}
