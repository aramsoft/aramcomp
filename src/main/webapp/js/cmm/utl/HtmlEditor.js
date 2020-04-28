
function initEditor() {

	var editor = new HTMLArea(_editor_area);

    // comment the following two lines to see how customization works
	//  editor.generate();
    // return false;

	var cfg = editor.config;
	
	cfg.registerButton("my-newline",  "Insert new line", editor.imgURL("images/ed_newline.gif"), false, clickHandler);
	cfg.toolbar = [
		[ "fontname", "space",
		  "fontsize", "space",
		  "formatblock", "space",
		  "bold", "italic", "underline", "separator",
		  "strikethrough", "subscript", "superscript", "separator",
		  "copy", "cut", "paste", "space", 
		  "undo", "redo" ],
		[ "justifyleft", "justifycenter", "justifyright", "justifyfull", "separator",
		  "outdent", "indent", "separator",
		  "forecolor", "hilitecolor", "textindicator", "separator",
		  "inserthorizontalrule", "createlink", "insertimage", "inserttable", "htmlmode", "separator",
		  "popupeditor", "separator", 
		  "showhelp", "about", /*"separator", "my-newline"*/]
	];
	cfg.fontname = {
		"&mdash; font &mdash;":         '',
		"굴림":    		   	"굴림, 굴림체",
		"돋움":            	"돋움, 돋움체",
		"바탕":    			"바탕, 바탕체",
		"궁서":    			"궁서, 궁서체",
		"Arial":	   		'arial,helvetica,sans-serif',
		"Courier New":	   	'courier new,courier,monospace',
		"Georgia":	   		'georgia,times new roman,times,serif',
		"Tahoma":	   		'tahoma,arial,helvetica,sans-serif',
		"Times New Roman": 	'times new roman,times,serif',
		"Verdana":	   		'verdana,arial,helvetica,sans-serif',
		"impact":	   		'impact',
		"WingDings":	   	'wingdings'
	};
	
	cfg.pageStyle = "body {font-size:0.75em; font-family:Verdana, Helvetica, '굴림', 'sans serif'}";

	editor.generate();
	return false;
}

function clickHandler(editor, buttonId) {
	switch (buttonId) {
    	case "my-newline":
 //   		editor.focusEditor();
 //   		editor.insertHTML("<br />");
    		break;
	}
}
