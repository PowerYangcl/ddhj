/**
 * 公共函数
 * 
 * @author zhy
 * @date 2016-04-11
 * @version 1.0.0
 */
$(function(){
	//操作开始前确认
	$.confirm=function(val,call,recall){
		$("#confirm-title").html(val);
		$("#confirm-modal").on("shown.bs.modal",function(){
			if(call){
				if(typeof call=="function"){
					$("#confirm-agree").bind("click",function(){
						$('#confirm-modal').modal('hide');
						call();
						//setTimeout("$('#confirm-modal').modal('hide')",1000);
					});
				}
			}
			if(recall){
				if(typeof recall=="function"){
					$("#confirm-close").bind("click",recall);
					setTimeout("$('#confirm-modal').modal('hide')",1000);
				}
			}
		});
		$("#confirm-modal").modal();
	}
	//操作完成提醒
	$.note=function(val,call){
		$("#note-title").html(val);
		$("#note-modal").modal();
		$("#note-modal").on("hidden.bs.modal",function(){
			if(call){
				(typeof call=="function") && call();
			}
		});
		setTimeout("$('#note-modal').modal('hide')",1000);
	}
});
