<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <html>
       <head>
          <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
           <link href="https://mdbootstrap.com/previews/docs/latest/css/bootstrap.min.css" rel="stylesheet">
           <link href="https://mdbootstrap.com/previews/docs/latest/css/mdb.min.css" rel="stylesheet">
     <style>
     	a{color:black}
     	span{color:black; font-weight: bold; font-size:30px; font-family:궁서}
     	ul:nth-child(1) {position: absolute; padding-left:150px}
     	input[id=mid-text]{border-radius: 5px;}
     	li{padding-right:10px; font-size:15px; font-size:18; }    
     	#header-nav{background-color:B3B3B3} 	
     	
     </style>
      </head>
      <body>
       <!--Main Navigation-->
      <header>
      <form>
      <nav class="navbar navbar-expand-lg navbar-dark  fixed-top" id="header-nav">
          <a class="navbar-brand" href="index.html"><img src="header_image.jpg">&nbsp;&nbsp;<span>우리 지금 만나</span></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
              aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span></button>
          <div class="collapse navbar-collapse " id="navbarSupportedContent">	
              <ul>
              	<img src="search_image.jpg">
              	<input type="text" maxlength="15" value=" Search.." id="mid-text" size=25px>
              </ul>
               
               <ul class="navbar-nav ml-auto nav-flex-icons">
               		<li class="nav-item">
               			<a href="">회원가입</a>&nbsp;&nbsp;&nbsp;
               		</li>
               		<li class="nav-item">
               			<a href="">로그인</a>
               		</li>
               </ul>
          </div>
      </nav>
      </form>
      </header>
      
   </body>
</html>