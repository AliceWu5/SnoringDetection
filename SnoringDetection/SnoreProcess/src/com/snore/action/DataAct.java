package com.snore.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.snore.service.UserService;
import com.snore.util.Data;
import com.snore.util.PageData;

@Controller
public class DataAct {

	private UserService userService = new UserService();

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String queryByDate(HttpServletRequest request, ModelMap model) {
		return "/data/snorequery";
	}

	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public String data(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String date)
			throws ServletException, IOException {
		System.out.println("�û��������ڣ�" + date);

		List<Data> datas = userService.queryData(date);
		String welcome = "";

		//��֤�û������Ƿ�ʹ�ù����
		if (datas.size() == 0) {
			welcome = "���� " + date + " ��һ��û��ʹ�ù��������";
			// ת������
			request.setAttribute("welcome", welcome);
			request.getRequestDispatcher("nodata.jsp").forward(request,
					response);
		} else {
			welcome = "���������� " + date + "��һ����������ݼ�¼";
			model.addAttribute("welcome", welcome);
		}

		//���ǰ��չʾ������
		List<PageData> pageDatas=new ArrayList<>();
		
		
		if (datas.size()!=0) {
			//��ŵ�һ��id
			int count=1;
			datas.get(0).setCount(1);
			
			//��data���ǩ
			for(int i=1;i<datas.size();i++){
				if (datas.get(i-1).getDateTime().equals(datas.get(i).getDateTime())) {
					datas.get(i).setCount(count);
					continue;
				}
				count++;
			}
			
			//����countѭ������,׼��չʾ�����ݼ���
			for(int i=1;i<=count;i++){
				PageData pageData=new PageData();
				double sleepTime=0;
				double snoreTime=0;
				double snoreBigTime=0;
				
				double snoreMax=0;
				String snoreMaxMoment="";
				String startDetectTime="";
				
				SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
				
				for (int j = 0; j < datas.size(); j++) {
					if (datas.get(j).getCount()==i) {
						//ͳ�ƴ�����ʱ��
						snoreTime=snoreTime+datas.get(j).getDuration();
						//ͳ��˯����ʱ��
						sleepTime=datas.get(j).getEndTime().getTime()
								-datas.get(j).getDateTime().getTime();
						//ͳ�ƽϴ������ʱ��
						if (datas.get(j).getMaximumValue()>0.88) {
							snoreBigTime=snoreBigTime+(int)datas.get(j).getDuration();
						}
						//ͳ��������ʱ��
						if (datas.get(j).getMeanValue()>snoreMax) {
							snoreMax=datas.get(j).getMeanValue();
							snoreMaxMoment=sdf.format(datas.get(j).getStartTime());
							
						}
						startDetectTime=sdf.format(datas.get(j).getDateTime());
						
					}
				}
				
				pageData.setId(i);
				pageData.setSleepTime((int)sleepTime/1000);
				pageData.setSnoreNormalTime((int)snoreTime);
				pageData.setSnoreBigTime((int)snoreBigTime);
				pageData.setSnoreNormalTime((int)(snoreTime-snoreBigTime));
				pageData.setSnoreMaxMoment(snoreMaxMoment);
				pageData.setSnoreTime((int)snoreTime);
				pageData.setStartDetectTime(startDetectTime);
				
				pageDatas.add(pageData);
			}
			model.addAttribute("count",count);
		}
		
		
		model.addAttribute("datas", datas);
		model.addAttribute("pageDatas",pageDatas);

		return "/data/snoredata";
	}

}
