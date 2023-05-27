package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.entity.ProductOrder;
import com.util.DBConnect;

public class OrderDao {

	private Connection conn;

	public OrderDao() {
		conn = DBConnect.getConnection();
	}

	public boolean saveOrder(List<ProductOrder> plist) {
		boolean f = false;
		try {

			String sql = "insert into product_order(user_id,product_id,order_id,quantity,price,payment_type,order_status,order_date) values(?,?,?,?,?,?,?,?)";

			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);

			for (ProductOrder p : plist) {
				ps.setInt(1, p.getUserId());
				ps.setInt(2, p.getProductid());
				ps.setString(3, p.getOrderId());
				ps.setInt(4, p.getQuantity());
				ps.setString(5, p.getPrice());
				ps.setString(6, p.getPaymentType());
				ps.setString(7, p.getOrderStatus());
				ps.setString(8, LocalDate.now().toString());
				ps.addBatch();

			}

			int[] count = ps.executeBatch();
			conn.commit();
			f = true;
			conn.setAutoCommit(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean updateStatus(String st, int id) {
		boolean f = false;
		try {

			String sql = "update product_order set order_status=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, st);
			ps.setInt(2, id);

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<ProductOrder> getOrderByUser(int userId) {
		List<ProductOrder> list = new ArrayList<ProductOrder>();
		ProductOrder o = null;

		try {

			String sql = "select * from product_order where user_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				o = new ProductOrder();
				o.setId(rs.getInt(1));
				o.setUserId(rs.getInt(2));
				o.setProductid(rs.getInt(3));
				o.setOrderId(rs.getString(4));
				o.setQuantity(rs.getInt(5));
				o.setPrice(rs.getString(6));
				o.setPaymentType(rs.getString(7));
				o.setOrderStatus(rs.getString(8));
				o.setOrderDate(rs.getString(9));
				list.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ProductOrder> getAllOrder() {
		List<ProductOrder> list = new ArrayList<ProductOrder>();
		ProductOrder o = null;

		try {

			String sql = "select * from product_order ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				o = new ProductOrder();
				o.setId(rs.getInt(1));
				o.setUserId(rs.getInt(2));
				o.setProductid(rs.getInt(3));
				o.setOrderId(rs.getString(4));
				o.setQuantity(rs.getInt(5));
				o.setPrice(rs.getString(6));
				o.setPaymentType(rs.getString(7));
				o.setOrderStatus(rs.getString(8));
				o.setOrderDate(rs.getString(9));
				list.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
