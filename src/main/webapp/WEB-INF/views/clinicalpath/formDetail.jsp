<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Cache-Control" content="must-revalidate,no-cache">
    <meta http-equiv="x-dns-prefetch-control" content="on" />
    <title>临床路径详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <section class="container detail">
        <header class="lclj-head">
            <h4>${clinicalPathName }<span>${specialistName }</span></h4>
            <p>标准住院日 <span>${standardDays }</span></p>
        </header>
        <div style="border-bottom: 1px solid #efefef;background: white;text-align: center;font-size: 3rem;height:25px;"><p >${pointName }</p></div>
		        	<article class='part'>
		        		<h5><span>1</span>主要诊疗工作</h5>
		        		<article class='content'><p>${treatmentWorks }</p></article>
		        	</article>
		        	<article class='part'>
		        		<h5><span>2</span>重点医嘱</h5>
		        		<article class='content'><p>${focusAdvice }</p></article>
		        	</article>
		        	<article class='part'>
		        		<h5><span>3</span>主要护理工作</h5>
		        		<article class='content'><p>${nursingWork }</p></article>
		        	</article>
		    
    </section>
<script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/touch.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>

    <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
            
    
</body>

</html>
