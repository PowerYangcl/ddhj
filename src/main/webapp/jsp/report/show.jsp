<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
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
            <div class="time">有效期至2017年6月1日</div>
            <div class="position">西山国际城</div>
            <div class="copyright">北京亿科云科有限公司版权所有</div>
            <div class="point"></div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="dir">目录</div>
            <div class="con">
                <p>区域环境质量总述</p>
                <p>区域环境质量排名</p>
                <p>区域环境质量评价</p>
                <p>各环境质量参数详解</p>
            </div>
            <div class="point"></div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="dir02">区域环境质量总述</div>
            <div class="point"></div>
            <div class="detail">
                <p class="txt">环境质量是指在一个具体的环境内，环境的总体或环境的某些要素，对人群的生存和繁衍以及社会经济发展的适宜程度，是反映人类的具体要求而形成的对环境评定的一种概念。到20世纪60年代，随着环境问题的出现，常用环境质量的好坏来表示环境遭受污染的程度。环境质量可分为自然环境质量和社会环境质量，本报告中主要针对您所在区域的自然环境质量，通过分析各项环境数据，为您的生活、学习、工作、居住、出行提供一定的参考与帮助。</p>
                <p class="txt">自然环境再细分可分为物理环境质量、化学环境质量及生物环境质量。物理环境质量是用来衡量周围物理环境条件的，比如自然界气候、水文、土壤、噪音、辐射源、绿化率、容积率等等。化学环境质量是指周围工业是否产生化学环境要素，如果周围的重污染工业比较多，那么产生的化学环境要素就多一些，产生的污染比较严重，化学环境质量就比较差。生物环境质量是针对周围生物群落的构成特点而言的。不同地区的生物群落结构及组成的特点不同，其生物环境质量就显出差别，生物群落比较合理的地区，生物环境质量就比较好，生物群落比较差的地区生物环境质量就比较差。由于您所在区域处于城市，生物群落相对比较单一，因此本报告主要分析您周边的物理环境质量和化学环境质量。</p>
            </div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <div class="detail detail02">
                <p class="txt">您所在小区位于（北纬XX+东经XX 具体定位），属于（（1）华北、东北区域（既北京，河北，天津，青岛，大连等城市）为温带季风气候，夏季夏季高温多雨，冬季寒冷干燥；（2）华东、华南等区域（既上海、广州、深圳、武汉、长沙，重庆，成都等城市）为亚热带季风气候，夏季高温多雨,冬季低温少雨；（3）（内蒙古及新疆区域）为温带大陆性气候，冬季寒冷，夏季炎热．终年干旱少雨，降水相对集中于夏季，（4）（西北区域如甘肃、西藏等）为高原山地气候，海拔高，气温低，但辐射强，日照丰富，降水少，冬半年风力强劲。气温的年较差小，日差较大。（4）（台湾、海南）为热带季风气候，全年高温，降水季节差异很大，分干季和雨季）。（其他区域可参照下表，下表不要写入正式环境质量报告中）。</p>
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
                        <tr>
                          <td>空气质量（AQI）</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>噪音</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>水质</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>土壤</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>高压电辐射</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>危险品存放</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>污染源</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>绿地率</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>容积率</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                      </tbody>
                    </table>
                </div>
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
                          <th class="cel-26">环境数据</th>
                          <th class="cel-26">排名</th>
                          <th class="cel-26">排名时效</th>
                          <th class="cel-26">排名区域</th>
                        </tr>
                      </thead>
                     <tbody>
                        <tr>
                          <td>空气质量（AQI）</td>
                          <td>2</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>噪音</td>
                          <td>3</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>水质</td>
                          <td>4</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>土壤</td>
                          <td>1</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>高压电辐射</td>
                          <td>2</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>危险品存放</td>
                          <td>4</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>污染源</td>
                          <td>100</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>绿地率</td>
                          <td>2</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                        <tr>
                          <td>容积率</td>
                          <td>3</td>
                          <td>近一个月</td>
                          <td>北京市内</td>
                        </tr>
                      </tbody>
                      </table>
				</div>
			</div>
		</li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <div class="detail detail02">
                <p class="txt">区域环境质量评价</p>
                <div class="detail_table">
                    <table border="1">
                      <thead>
                        <tr>
                          <th class="cel-26">环境参数</th>
                          <th class="cel-37">等级</th>
                          <th class="cel-37">评价</th>
                      </thead>
                     <tbody>
                        <tr>
                          <td>空气质量（AQI）</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>噪音</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>水质</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>土壤</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>高压电辐射</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>危险品存放</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>污染源</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>绿地率</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                        <tr>
                          <td>容积率</td>
                          <td>100</td>
                          <td>优</td>
                        </tr>
                     </tbody>
                     </table>
                </div>
           	</div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="dir02">各环境质量参数详解</div>
            <div class="point"></div>
            <div class="detail">
                <h2>空气质量</h2>
                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。</p>
                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。</p>
                <h2>噪音</h2>
                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬浮颗粒物：是指漂浮在空气中的</p>
            </div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <div class="detail detail02">
                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬</p>
                <div class="detail_table">
                    <table border="1">
                      <thead>
                        <tr>
                          <th class="cel-26">Month</th>
                          <th class="cel-37">Savings</th>
                          <th class="cel-37">Savings</th>
                        </tr>
                      </thead>

                      <tbody>
                        <tr>
                          <td>January</td>
                          <td>$100</td>
                          <td>$100</td>
                        </tr>
                        <tr>
                          <td>February</td>
                          <td>$80</td>
                          <td>$80</td>
                        </tr>
                      </tbody>
                    </table>
                </div>
            </div>
            <p class="botm">点击下载<a href="javascript:;">《区域环境质量报告正式版》</a>可了解更多环境质量参数数值、等级划分标准，以及区域环境质量详细评价。</p>
            <div class="downloadBtn">立即下载正式版</div>
       </li>
       <li>
            <img class="bg_logo02" src="images/report/bg_logo02.png">
            <div class="point"></div>
            <div class="detail detail02">
                <p class="txt">空气质量直接反映空气的污染程度，它是依据空气中污染物浓度的高低来判断的。空气中的污染物主要包括：总悬浮颗粒物：是指漂浮在空气中的固态和液态颗粒物的总称，其粒径范围约为0.1-100 微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。微米。通常把环境空气中空气动力学当量直径在10微米以下的颗粒物称为PM10。微米。通常把环境空气中空气动力学当量直径在1气动力学当量直径在10微米以下的颗粒物称为PM10。</p>
                <img class="chinaImg" src="images/report/qihou.jpg">
            </div>
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