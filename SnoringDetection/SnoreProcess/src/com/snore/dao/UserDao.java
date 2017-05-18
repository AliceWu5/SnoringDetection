package com.snore.dao;

import java.util.List;

import com.snore.entity.User;

public interface UserDao {
	

	/**
	 * ����id��ѯ�û���Ϣ
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User selectUserById(int id) throws Exception;
	
	/**
	 * �������ڲ�ѯ�û���Ϣ
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<User> selectUserByDate(String date) throws Exception;

	/**
	 * ��ѯ���е��û���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUser() throws Exception;
	
	/**
	 * ɾ��ĳ�������
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteData(String date);
	
	
}
