<#-- 环境报告h5模板 -->
<!DOCTYPE html>
<html>
<head>
<base id="base" href="http://api.sys.ecomapit.com/ddhj/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>点点-环境报告</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="css/report/base.css" />
<link rel="stylesheet" type="text/css" href="css/report/style.css" />
<style>
</style>
</head> 
<body>
<div class="verticalPage">
   <ul id="pages">
       <li>
            <img class="bg_logo01" src="images/report/bg_logo01.png">
            <h1><span>楼盘质量报告</span></h1>
            <div class="position">${title}</div>
            <div class="copyright">北京亿科云科有限公司版权所有</div>
            <div class="point"></div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="dir02">区域环境质量总述</div>
            <div class="point"></div>
            <div class="detail">
                <p class="txt">环境质量是指在一个具体的环境内，环境的总体或环境的某些要素，对人群的生存和繁衍以及社会经济发展的适宜程度，是反映人类的具体要求而形成的对环境评定的一种概念。到20世纪60年代，随着环境问题的出现，常用环境质量的好坏来表示环境遭受污染的程度。环境质量可分为自然环境质量和社会环境质量，本报告中主要针对您所在区域的自然环境质量，通过分析各项环境数据，为您的生活、学习、工作、居住、出行提供一定的参考与帮助。</p>
                <p class="txt">自然环境再细分可分为物理环境质量、化学环境质量及生物环境质量。物理环境质量是用来衡量周围物理环境条件的，比如自然界气候、水文、土壤、噪音、辐射源、绿化率、容积率等等。化学环境质量是指周围工业是否产生化学环境要素，如果周围的重污染工业比较多，那么产生的化学环境要素就多一些，产生的污染比较严重，化学环境质量就比较差。生物环境质量是针对周围生物群落的构成特点而言的。不同地区的生物群落结构及组成的特点不同，其生物环境质量就显出差别，生物群落比较合理的地区，生物环境质量就比较好，生物群落比较差的地区生物环境质量就比较差。由于您所在区域处于城市，生物群落相对比较单一，因此本报告主要分析您周边的物理环境质量和化学环境质量。</p>
                <p class="txt">您所在小区位于（北纬${lat},东经${lng}），属于${weatherDistribution}</p>
            </div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="dir02">区域环境质量总述</div>
            <div class="point"></div>
            <div class="detail">
                <p class="txt">您所在小区位于（北纬${lat},东经${lng}），属于${weatherDistribution}</p>
            </div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <div class="detail detail02">
                <p class="txt">区域附近一个月内各项自然环境质量参数 如下表所示</p>
                <div class="detail_table">
                    <table border="1">
                      <thead>
                        <tr>
                          <th class="cel-26">环境参数</th>
                          <th class="cel-37">参数值</th>
                          <th class="cel-37">等级</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<#list environmentIndexs as env>
                        <tr>
                          <td>${env.name}</td>
                          <td>${env.value}</td>
                          <td>${env.level}</td>
                        </tr>
                        </#list>
                      </tbody>
                    </table>
                </div>
            </div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <p class="botm">点击下载<a href="javascript:;">《区域环境质量报告正式版》</a>可了解更多环境质量参数数值、等级划分标准，以及区域环境质量详细评价。</p>
            <div class="downloadBtn">立即下载正式版</div>
       </li>

   </ul>
</div>
<script src="js/plugins/jquery-2.1.4-min.js"></script>
<script src="js/plugins/jquery.easing.1.3.js"></script>
<script>
    $(function(){
        $("#pages li").height($(window).height());
        //调用拖拽函数
        drag(document.getElementById("pages"));
        //拖拽函数
        function drag(obj){  
            var oHeight=$(window).height();
            var speed = 0;
            var count = 0;
            obj.addEventListener('touchstart', function (ev){
                var oTouch=ev.targetTouches[ev.targetTouches.length-1];
                var move_start =oTouch.pageY;
                var disY=oTouch.pageY-obj.offsetTop;
                var id=oTouch.identifier;
                function move(ev)
                {   
                    for(var i=0;i<ev.targetTouches.length;i++)
                    {
                        if(ev.targetTouches[i].identifier==id)
                        {   
                            // obj.style.top=ev.targetTouches[i].pageY-disY+'px';
                            speed = ev.targetTouches[i].pageY-move_start;
                            console.log(speed);
                        }
                    }
                    //算速度
                }
                function end(ev)
                {
                    for(var i=0;i<ev.changedTouches.length;i++)
                    {
                        if(id==ev.changedTouches[i].identifier)
                        {
                            obj.removeEventListener('touchmove', move, false);
                            obj.removeEventListener('touchend', end, false);
                        }
                    }
                    if(speed>10){
                        if(count<0){
                            count++;
                        }
                    }else if(speed<-10){
                        if(count>(1-$("#pages li").length)){
                            count--;
                        }
                    }
                    $(obj).stop(true).animate(
                        {"top":count*oHeight+'px'},
                        { 
                            easing: 'easeOutCirc',
                            duration: 300
                        }
                    );
                }
                obj.addEventListener('touchmove', move, false);
                obj.addEventListener('touchend', end, false);
                ev.preventDefault();
            }, false);
        }
    })
</script>
</body>
</html>