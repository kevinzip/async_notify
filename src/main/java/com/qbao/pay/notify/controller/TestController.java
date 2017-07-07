package com.qbao.pay.notify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qbao.pay.notify.listener.RabbitMqSend;
import com.qbao.pay.notify.view.BaseView;


@RequestMapping("/test")
@RestController
public class TestController extends BaseController{
	
	@Autowired
    private RabbitMqSend rabbitMqSend;

	
	@RequestMapping("/main")
	public String main() {
//		TestEntity t = testService.selectByUserId(1L);
//		System.out.println(JSON.toJSON(t));
		String str = "{\"outTradeNo\":1122009987,\"notifyUrl\":\"http://192.168.1.8:8080/acturl.html\",\"result\":{\"creator\":\"admin\",\"email\":\"9123344569@qq.com\",\"gmtCreated\":\"1490760835000\"}}";
		String str1 = "{\"outTradeNo\":1122009987,\"notifyUrl\":\"\",\"result\":{\"creator\":\"admin\",\"email\":\"9123344569@qq.com\",\"gmtCreated\":\"1490760835000\"}}";
//		for(int i=0;i<1;i++){
//			rabbitMqSend.send(str);
//		}
		rabbitMqSend.send(str);
		rabbitMqSend.send(str1);
		
		return "success";
	}
	
//	@RequestMapping("/send")
//	public String send(String ex, String qu,String routKey) throws IOException, TimeoutException {
//		new BaseConnector(qu,ex,routKey);
//		return "success";
//	}
	
	@ResponseBody
    @RequestMapping("/checkLogin")
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public BaseView checkLogin(@RequestParam Long id) {
		
		BaseView bv = new BaseView();
		try {
            bv.setMessage("查询成功!");
            bv.setResult("dsdsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
		return bv;
		
	}

}
