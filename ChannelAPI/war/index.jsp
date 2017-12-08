<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String token = (String)request.getAttribute("token");
    
    
    
    
    %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src='/_ah/channel/jsapi'></script>
</head>
<body>
<body>
  <script>
  function onOpened() {
  	  alert("onOpened");
  }
  function onMessage(m) {
	  
	  alert(m.data);
  }
    channel = new goog.appengine.Channel('<%=token%>');
    socket = channel.open();
    socket.onopen = onOpened;
    socket.onmessage = onMessage;
    
    
     function sendMessage (msg) {
    	  path = '/game?msg='+msg; 
    	  var xhr = new XMLHttpRequest();
    	  xhr.open('GET', path, true);
    	  xhr.send();
    		alert("send");
    	}

    	 
      	  
      	  
      	
    	function test()
        {
        	sendMessage("aaaaaaa");
        
        }
  </script>
  <button onclick="test()">test</button>
</body>
</body>
</html>