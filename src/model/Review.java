/**
 * @author cvandera
 *
 */

package model;

public class Review {

	private int id;
	private String review;
	private String userId;
	private String dishId;

	public Review(int id, String review, String userId, String dishId) {
		super();
		this.id = id;
		this.review = review;
		this.userId = userId;
		this.dishId = dishId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

}
