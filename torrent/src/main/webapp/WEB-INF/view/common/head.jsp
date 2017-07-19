<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basepath" value="${pageContext.request.contextPath}" />
<c:set var="path" value="${pageContext.request.contextPath}" />

<script src="${basepath}/assets/jquery-easyui-1.5/jquery.min.js" type="text/javascript"></script>

<script>
	function serializeForm(formObj) {
		var paramObject = new Object();
		var elementsObj = formObj.elements;
		var obj;
		for (var i = 0; i < elementsObj.length; i += 1) {
			obj = elementsObj[i];
			if (obj.name != undefined && obj.name != "") {
				paramObject[obj.name] = obj.value;
			}
		}
		return JSON.stringify(paramObject);
	};
	
	var appInfo = {
		basepath:'${basepath}',
		path:'${path}'
	}
</script>