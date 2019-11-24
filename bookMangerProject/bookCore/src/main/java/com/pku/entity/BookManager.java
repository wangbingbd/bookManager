package com.pku.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pku.base.BaseEntity;

@Entity
@Table(name="pku_book_manager")
public class BookManager extends BaseEntity{

	private static final long serialVersionUID = 7916659184015149013L;

	@JoinColumn(name = "book_id")
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.REFRESH)
    @Basic(fetch = FetchType.LAZY)
    private Book book;
	
	@JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH)
    @Basic(fetch = FetchType.LAZY)
    private User user;
	
	@Column(name = "borrow_time",columnDefinition="datetime COMMENT '借阅日期'")
	private Date borrowTime;
	
	@Column(name = "return_time",columnDefinition="datetime COMMENT '归还日期'")
	private Date returnTime;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
}

