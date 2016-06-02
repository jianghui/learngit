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
    <title>疾病详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <section class="container detail">
        <h3 id="name"></h3>
        
        <footer class="btn">
            <a id="goTop" href="#" class="icon-btn"><img src="${ctx}/res/img/go_top.png" alt=""></a>
            <a id="menu" href="javascript:void(0);" class="icon-btn"><img src="${ctx}/res/img/menu.png" alt=""></a>
        </footer>
    </section>
    <section class="nav-layer" id="nav-layer">
        <article class="left"></article>
        <ul class="nav">
        <div id="biaoshi"></div>
        </ul>
    </section>
    <input type="hidden" value="${diseaseEntityId}" id="diseaseEntityId">
      <input type="hidden" value="${diseaseEntityName}" id="diseaseEntityName">
       
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/touch.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
    <script type="text/javascript">
    $(function() {
        var diseaseEntityId = $("#diseaseEntityId").val(),
            diseaseEntityName = $("#diseaseEntityName").val();

        $.ajax({
            url: '${ctx}/disease/queryGuidelines',
            type: 'POST',
            dataType: 'json',
            data: {keyValue: diseaseEntityId},
            success: function(res) {
                var data = res.data;
		        $("#name").html(data.diseaseName);
                var arr = [{
                    "name": "诊疗概述",
                    "shuxing": data.treatmentOverview
                }, {
                    "name": "流行病学",
                    "shuxing": data.epidemiology
                }, {
                    "name": "病因",
                    "shuxing": data.cause
                }, {
                    "name": "发病机制",
                    "shuxing": data.pathogenesis
                }, {
                    "name": "临床表现",
                    "shuxing": data.clinicalManifestation
                }, {
                    "name": "并发症",
                    "shuxing": data.complication
                }, {
                    "name": "实验室检查",
                    "shuxing": data.laboratoryExamination
                }, {
                    "name": "其他检查",
                    "shuxing": data.otherExamination
                }, {
                    "name": "临床诊断",
                    "shuxing": data.clinicalDiagnosis
                }, {
                    "name": "鉴别诊断",
                    "shuxing": data.differentialDiagnosis
                }, {
                    "name": "治疗措施",
                    "shuxing": data.therapeuticMeasures
                }, {
                    "name": "预后事项",
                    "shuxing": data.prognosisMatters
                }, {
                    "name": "预防措施",
                    "shuxing": data.preventiveMeasures
                }, {
                    "name": "数据来源",
                    "shuxing": data.dataSources
                }];
                console.log(arr);
                var arr_result = _.filter(arr, function(el) {
                    return el.shuxing !== undefined
                });
                arr_result = _.filter(arr_result, function(el) {
                    return el.shuxing !== "null"
                });
                console.log(arr_result);
                for (var i = arr_result.length - 1; i >= 0; i--) {
                    var li_html = $("<li><a href='javascript:void(0);' data-href='#part" + (i + 1) + "'>" + arr_result[i].name + "</a></li>");
                    var article_html = $("<article class='part' id='part" + (i + 1) + "'><h5><span>" + (i + 1) + "</span>" + arr_result[i].name + "</h5><article class='content'><p>" + arr_result[i].shuxing + "</p></article></article>");
                    $("#name").after(article_html);
                    $("#biaoshi").after(li_html);
                }
                $(".nav").find("li").eq(0).addClass('active')

            }
        })



    })
    </script>
    <script type="text/javascript">
    Zepto(function($) {
        // 回到顶部
        window.onscroll = function() {
            var move = $(window).scrollTop();
            if (move > 100) {
                $("#goTop").css('visibility', 'visible');
            } else {
                $("#goTop").css('visibility', 'hidden');
            }
        }
        $("#goTop").tap(function() {
            $(window).scrollTop(0);
        });

        // 菜单按钮
        $("#menu").tap(function() {
            $("#nav-layer").show();
        });
        $("#nav-layer .left").tap(function(event) {
            $("#nav-layer").hide();
        });

        $("#nav-layer").delegate('a', 'tap', function(event) {
            event.preventDefault();
            var oPart = $(this).data('href');
            var oScrollerHeight = $(oPart).offset().top;
            $(window).scrollTop(oScrollerHeight);
            $(this).parent().addClass('active').siblings('li').removeClass('active');
        });
    })
    </script>
</body>

</html>
