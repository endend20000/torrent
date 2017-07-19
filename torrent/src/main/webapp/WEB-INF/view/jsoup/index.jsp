<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/common/easycommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>资源搜索</title>
    <meta charset="utf-8">
    <style>
    	html, body{ margin:0; height:100%; }
    	table{font-size:18px}
    	.input{width:250px;margin:4px;height:35px;border: 1px solid #ccc;font-size:18px;border-radius:4px}
    	label{margin:4px}		
    	.btn{width:100px;height:35px;border-radius:4px;background:#6CAEF5;font-size:18px;margin:15px}	
    </style>
    <script>
    
    $(function(){

        easyloader.load('datagrid', function(){   		 
        	
    	var datagrid; //定义全局变量datagrid
        var editRow = undefined; //定义全局变量：当前编辑的行
        
        var codedatagrid; //定义全局变量datagrid
        var codeeditRow = undefined; //定义全局变量：当前编辑的行
        
        var selectedType=undefined;

        datagrid = $('#torrent').datagrid({
    		rownumbers:true,
    		singleSelect:true,
    		pagination:true,
    		fit: true, //datagrid自适应宽度
            fitColumn: true, //列自适应宽度
            striped: true, //行背景交换
            nowap: false, //列内容多时自动折至第二行
            border: false,
            idField: 'id', //主键
            pageSize:30,
            pageList:[15,30,45],
/*             width:710,
            height:'98%', */
    		url:"${path}/jsoup/data.json",
    		columns:[[
    			//{field:'id', title: '编号', sortable: true, checkbox: true,fitColumns:true },
    			{field:'text',title:'标题',width:'50%','sortable':true,fitColumns:true },
    			{field:'createdTime',title:'创建时间',width:'12.5%',fitColumns:true},
    			{field:'size',title:'大小',width:'12.5%',fitColumns:true},    		
    			{field:'hot',title:'热度',width:'12.5%',fitColumns:true},  
    			{field:'_operator',title:'操作',width:'12.5%',fitColumns:true,formatter:
    				function(value,row,index){ 				 
    				 var a="<a href='#' onclick=download2('"+row.href+"',0)>磁力链<a/>";// <a href='#' onclick=download2('"+row.href+"',1)>种子下载<a/>";
    				 return a;
    				}
    			}
    		]],
            toolbar: '#tb'
    		})   	
     });
 })

    var searchContent = function(){
        var seachform=$('#searchForm').form();
        $('#torrent').datagrid('load',serializeObject(seachform));
      }
    
    var download2 = function(url,type){
    	$.ajax({
			 	url: "${path}/jsoup/torrent.json",    //请求的url地址
			 	dataType: "json",   //返回格式为json
			 	async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			 	data: { "url": url,"type":type },    //参数值
			 	type: "get",  
   			 	success: function(req) {
   			 		if(req.status=='1'){
   			 			if(type==0){
   			 				//alert(req.dataset);
   			 				window.location.href=req.dataset
   			 			}else{
   			 			    window.open(req.dataset); 
   			 			}
   			 		   
   			 			//alert(req.dataset);
   			 		}else{
   			 			$.messager.alert("异常", req.msg,"error"); 
   			 		}                			 		
				},
				error:function(req) {
					console.log(req);
					$.messager.alert("警告", "请求出错","error"); 
				}
		});
	}
 
    
    </script>
</head>
<body>
		 	<table id="torrent" title="资源搜索" class="easyui-datagrid" 
					singleSelect="true" iconCls="icon-save">
			</table>
			
			<div id="tb" >
  <form id="searchForm" class="easyui-form"  style="height:13px">
    <input class="easyui-textbox" type="text" id="keyword" name="keyword" style="width:150px" data-options="prompt:'关键词'"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchContent()">搜索</a>
  </form>
</div>
</body>
</html>
      