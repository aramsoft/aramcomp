/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();
var openTreeNodes	    = new Array();
var vHtmlCode       = "";


/*
* 트리생성함수
*/
function createTree(arrName, fnName) {
   var startNode, openNode;
	treeNodes = arrName;
	if (treeNodes.length > 0) {

		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var nodeValues = treeNodes[getTreeArrayId(startNode)].split("|");
		}
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes, fnName);
	}
	return vHtmlCode;
}
/*
* 노드위치 확인
*/
function getTreeArrayId(node) {
	for (i=0; i<treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[0]==node) return i;
	}
	return 0;
}
/*
* 트리 노드 열기
*/
function setOpenTreeNodes(openNode) {
	for (i=0; i<treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[0]==openNode) {
			openTreeNodes.push(nodeValues[0]);
			setOpenTreeNodes(nodeValues[1]);
		}
	} 
}

/*
* 하위 트리노드 존재여부 확인
*/
function hasChildTreeNode(parentNode) {
	for (i=0; i< treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}

/*
* 트리노드 최하위 여부 확인
*/
function lastTreeSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode) lastChild = nodeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}


/*
* 신규 트리노드 추가
*/
function addTreeNode(parentNode, recursedNodes, fnName) {
	for (var i = 0; i < treeNodes.length; i++) {
		var nodeValues = treeNodes[i].split("|");
		if (nodeValues[1] == parentNode) {
			
			var lastSibling	= lastTreeSibling(nodeValues[0], nodeValues[1]);
			var hasChildNode	= hasChildTreeNode(nodeValues[0]);

			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);
			
			if (hasChildNode){
				vHtmlCode += '<div data-role="collapsible" data-collapsed="true">';
				vHtmlCode += '<h3>' + nodeValues[2] + '</h3>';
				vHtmlCode += '<ul data-role="listview" id="site" data-inset="true" data-theme="d">';
            }else{
            	
				vHtmlCode += '<li><a href="javascript:' + fnName + '(\'' + nodeValues[4] + '\')" rel="external">' + nodeValues[2] + '</a></li>';
            }
			if (hasChildNode) {

				addTreeNode(nodeValues[0], recursedNodes, fnName);
				vHtmlCode += '</ul>';
				vHtmlCode += "</div>";
			}
			
			recursedNodes.pop();

		}
	}
}

if(!Array.prototype.push) {
	function fnArrayPush() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = fnArrayPush;
}
if(!Array.prototype.pop) {
	function fnArrayPop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = fnArrayPop;
}

