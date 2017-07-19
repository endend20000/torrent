<%@ include file="/WEB-INF/view/common/head.jsp"%>

<!-- easyui -->
<link rel="stylesheet" type="text/css" href="${basepath}/assets/jquery-easyui-1.5/themes/bootstrap/easyui.css"> 
<link rel="stylesheet" type="text/css" href="${basepath}/assets/jquery-easyui-1.5/themes/icon.css">

<%-- <script type="text/javascript" src="${basepath}/assets/jquery-easyui-1.5/jquery.easyui.min.js"></script>  --%>
<script type="text/javascript" src="${basepath}/assets/jquery-easyui-1.5/easyloader.js"></script>


<%-- <script type="text/javascript" src="${basepath}/assets/my97/WdatePicker.js"></script> --%>


<script>
easyloader.base = '${basepath}/assets/jquery-easyui-1.5/';

function JsonCode(formObj){
    var paramObject = new Object();  
    var elementsObj=formObj.elements;  
    var obj;  
    for(var i=0; i<elementsObj.length;i+=1){  
        obj=elementsObj[i];  
        if(obj.name!=undefined&&obj.name!=""){  
            paramObject[obj.name] = obj.value;  
        }  
    }  
    return JSON.stringify(paramObject);  
};

Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), 
		"S" : this.getMilliseconds() //millisecond 
	} 
	
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	}
	
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
} 
function serializeObject(form){
    var o={};
    $.each(form.serializeArray(),function(index){
        
              if(o[this['name'] ]){
           
                   o[this['name'] ] = o[this['name'] ] + "," + this['value'];

               }else{

                  o[this['name'] ]=this['value'];

               }

        })

      return o;
}    
</script>
<script type="text/javascript" src="${basepath}/assets/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script> 