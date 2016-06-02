<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>药品百科</title>
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/css/css.css">
</head>

<body>
    <div class="yp_search" id="search">
        <input type="text" placeholder="请输入药品名称搜索" id="myInput" />
        <button class="confirm" disabled="disabled">搜索</button>
        <button class="cancel">取消</button>
    </div>
    <div class="yp_list list">
        <ul class="yp_list_tap">
            <li class="cur">西药</li>
            <li>中成药</li>
        </ul>
        <div class="yp_list_con">
        </div>
        <div class="yp_list_con none">
        </div>
    </div>
    <div class="search_result">
       
    </div>
    <!-- <div class="more" id="more">加载更多</div> -->
    <script type="text/javascript" src="${ctx}/res/js/zepto.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/res/js/underscore-min.js"></script>
    <script type="text/javascript">
    $(function() {
        
         $("#myInput").val("");
        $.ajax({
            url: '${ctx}/medication/queryMedicationTree',
            type: 'POST',
            dataType: 'json',
            // data: {param1: 'value1'},
            success: function(res) {
                var data = res.data;
                var type1 = _.where(data, {
                        'type': 1
                    }),
                    type2 = _.where(data, {
                        'type': 2
                    });
                  
                $.each(type2, function(index, item) {
                    var dl_html = $("<dl><dt data-encodingId='" + item.encodingId + "' data-name='" + item.medicationTreeName + "'><h3>" + item.medicationTreeName + "</h3></dt><dd></dd></dl>");
                    $(".yp_list").find(".yp_list_con").eq(0).append(dl_html);
                });
                $.each(type1, function(index, item) {
                        var dl_html = $("<dl><dt data-encodingId='" + item.encodingId + "' data-name='" + item.medicationTreeName + "'><h3>" + item.medicationTreeName + "</h3></dt><dd></dd></dl>");
                        $(".yp_list").find(".yp_list_con").eq(1).append(dl_html);
                    })
                    //点击回去二级列表
                $(".yp_list_con dl").on('click', 'dt', function(event) {

                    event.preventDefault();
                    $(this).next().html("");
                    //一级的encodingid（用于ajax参数）和名称（用于传给a链接然后在二级栏目显示） 
                    var oneLevelEncodingid = $(this).attr("data-encodingid"); //url的encodingid用这个变量
                    var oneLevelName = $(this).attr("data-name"); 
                    var that = $(this);
                    $.ajax({
                        url: '${ctx}/medication/queryMedicationByTreeChild',
                        type: 'POST',
                        dataType: 'json',
                        data: {encodingId: oneLevelEncodingid},
                        success: function(res) {
                            console.log(res);
                            var data = res.data;

                            $.each(data, function(index, item) {

                                var dd_html = $("<a href='${ctx}/view/medication_type?twoEncodingId=" + item.encodingId + "&oneLevelName=" + oneLevelName + "&medicationTreeName=" + item.medicationTreeName + "'>" + item.medicationTreeName + "</a>");

                                that.next().append(dd_html);
                            });


                        }
                    });
                    $(this).parent("dl").toggleClass('cur');


                });

            }

        })

        $(".yp_list_tap").on('click', 'li', function(event) {
            event.preventDefault();
            $(this).addClass('cur').siblings('li').removeClass('cur');
            var index = $(".yp_list_tap li.cur").index();
            $(".yp_list_con").eq(index).removeClass('none').siblings('div').addClass('none');
        });

        
        $("#search").on('click', '.confirm', function(event) {
            event.preventDefault();
            $(this).hide().next(".cancel").show();
            var input_val= $("#myInput").val();
            
              $.ajax({
                url: '${ctx}/medication/queryMedicationsByKey',
                type: 'POST',
                dataType: 'json',
                data: {keyValue: input_val,pageNum:1,rows:200},
                success:function(res){

                    var data=res.data;
                    $(".search_result").html("");
                    $.each(data, function(index, item) {
                        var search_result_html =$("<a href='${ctx}/view/medication_detail?medicationId=" + item.medicationId +"' >" + item.medicationName + "<p>"+item.originName+"</p></a>");
                        $(".search_result").append(search_result_html);
                    })

                }
            })
        });
      

    })
    </script>
</body>

</html>