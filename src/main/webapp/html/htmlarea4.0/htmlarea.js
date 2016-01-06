// htmlArea v4.0 - Copyright (c) 2005-2013 Inferior-Products.COM
// htmlArea v3.0 - Copyright (c) 2003-2005 dynarch.com
//                               2002-2003 interactivetools.com, inc.
// This copyright notice MUST stay intact for use (see license.txt).
//
// A free WYSIWYG editor replacement for <textarea> fields.
// For full source code and docs, visit http://Inferior-Products.com/
//
// Version 4.0 developed by the htmlarea developers - Inferior-Products.com
// Version 3.0 developed by Mihai Bazon, Dynarch.com.
//   http://www.dynarch.com/
//
// $Id: htmlarea.js,v 1.102 2013-07-24 16:21:50 sfchris Exp $
(function() {
	if (typeof _editor_url == "string") {
		_editor_url = _editor_url.replace(/\x2f*$/, '/');
	} else {
		alert("WARNING: _editor_url is not set!  You should set this variable to the editor files path; " +
		      "it should preferably be an absolute path, like in '/htmlarea/', but it can be relative if you prefer.  " +
		      "Further we will try to load the editor files correctly but we'll probably fail.");
		_editor_url = '';
	}
	if (typeof _editor_lang == "string") {
		_editor_lang = _editor_lang.toLowerCase();
	} else {
		_editor_lang = "en";
	}
	var agt = navigator.userAgent.toLowerCase();
	HTMLArea.is_ie = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
	HTMLArea.is_opera = window.opera ? true : (agt.indexOf("opera") != -1);
	HTMLArea.is_mac = (agt.indexOf("mac") != -1);
	HTMLArea.is_mac_ie = (HTMLArea.is_ie && HTMLArea.is_mac);
	HTMLArea.is_win_ie = (HTMLArea.is_ie && !HTMLArea.is_mac);
	HTMLArea.is_gecko = (navigator.product == "Gecko");
	HTMLArea.is_midas = HTMLArea.is_opera || HTMLArea.is_gecko;
})();

function HTMLArea(textarea, config) {
	if (HTMLArea.checkSupportedBrowser()) {
		if (typeof config == "undefined") {
			this.config = new HTMLArea.Config();
		} else {
			this.config = config;
		}
		this._htmlArea = null;
		this._textArea = textarea;
		this._editMode = "wysiwyg";
		this.plugins = {};
		this._timerToolbar = null;
		this._timerUndo = null;
		this._undoQueue = new Array(this.config.undoSteps);
		this._undoPos = -1;
		this._customUndo = false;
		this._mdoc = document;
		this.doctype = '';
	}
};
HTMLArea.onload = function() {};
HTMLArea._scripts = [];
HTMLArea.loadScript = function(url, plugin) {
	if (plugin)
		url = HTMLArea.getPluginDir(plugin) + '/' + url;
	this._scripts.push(url);
};

HTMLArea.init = function() {
	var head = document.getElementsByTagName("head")[0];
	var current = 0;
	var savetitle = document.title;
	var evt = HTMLArea.is_ie ? "onreadystatechange" : "onload";
	function loadNextScript() {
		if (current > 0 && HTMLArea.is_ie
				&& !/loaded|complete/.test(window.event.srcElement.readyState))
			return;
		if (current < HTMLArea._scripts.length) {
			var url = HTMLArea._scripts[current++];
			document.title = "[HTMLArea: loading script " + current + "/"
					+ HTMLArea._scripts.length + "]";
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.src = url;
			script[evt] = loadNextScript;
			head.appendChild(script);
		} else {
			document.title = savetitle;
			HTMLArea.onload();
		}
	};
	loadNextScript();
};

HTMLArea.loadScript(_editor_url + "dialog.js");
HTMLArea.loadScript(_editor_url + "popupwin.js");
HTMLArea.loadScript(_editor_url + "colors.js");
HTMLArea.loadScript(_editor_url + "lang/" + _editor_lang + ".js");
HTMLArea.RE_tagName = /(<\/|<)\s*([^\t\n>]+)/ig;
HTMLArea.RE_doctype = /(<!doctype((.|\n)*?)>)\n?/i;
HTMLArea.RE_head = /<head>((.|\n)*?)<\/head>/i;
HTMLArea.RE_body = /<body>((.|\n)*?)<\/body>/i;

HTMLArea.Config = function() {
	this.version = "4.0";
	this.width = "100%";
	this.height = "auto";
	this.statusBar = true;
	this.htmlareaPaste = false;
	this.undoSteps = 20;
	this.undoTimeout = 500;
	this.sizeIncludesToolbar = true;
	this.fullPage = false;
	this.pageStyle = "";
	this.killWordOnPaste = true;
	this.makeLinkShowsTarget = true;
	this.baseURL = document.baseURI || document.URL;
	if (this.baseURL && this.baseURL.match(/(.*)\/([^\/]+)/))
		this.baseURL = RegExp.$1 + "/";
	this.imgURL = "images/";
	this.popupURL = "popups/";
	this.htmlRemoveTags = null;
	this.toolbar = [
		[ "fontname", "space", "fontsize", "space", "formatblock", "space",
		  "bold", "italic", "underline", "strikethrough",
		  "separator", "subscript", "superscript", "separator",
		  "copy", "cut", "paste", "space", "undo", "redo", "space",
		  "removeformat", "killword" ],
		[ "justifyleft", "justifycenter", "justifyright", "justifyfull",
		  "separator", "lefttoright", "righttoleft", "separator",
		  "orderedlist", "unorderedlist", "outdent", "indent",
		  "separator", "forecolor", "hilitecolor", "separator",
		  "inserthorizontalrule", "createlink", "insertimage",
		  "inserttable", "toggletableborders", "htmlmode",
		  "separator", "popupeditor", "separator", "showhelp",
		  "about" ] 
	];
	this.fontname = {
		"&mdash; font &mdash;" : '',
		"Arial" : 'arial,helvetica,sans-serif',
		"Courier New" : 'courier new,courier,monospace',
		"Georgia" : 'georgia,times new roman,times,serif',
		"Tahoma" : 'tahoma,verdana,arial,helvetica,sans-serif',
		"Times New Roman" : 'times new roman,times,serif',
		"Verdana" : 'verdana,tahoma,arial,helvetica,sans-serif',
		"impact" : 'impact',
		"WingDings" : 'wingdings'
	};
	this.fontsize = {
		"&mdash; size &mdash;" : "",
		"1 (8 pt)"  : "1",
		"2 (10 pt)" : "2",
		"3 (12 pt)" : "3",
		"4 (14 pt)" : "4",
		"5 (18 pt)" : "5",
		"6 (24 pt)" : "6",
		"7 (36 pt)" : "7"
	};
	this.formatblock = {
		"&mdash; format &mdash;" : "",
		"Heading 1" : "h1",
		"Heading 2" : "h2",
		"Heading 3" : "h3",
		"Heading 4" : "h4",
		"Heading 5" : "h5",
		"Heading 6" : "h6",
		"Normal" : "p",
		"Address" : "address",
		"Formatted" : "pre"
	};
	this.customSelects = {};
	function cut_copy_paste(e, cmd, obj) {
		e.execCommand(cmd);
	};
	this.debug = true;
	this.btnList = {
		bold:			[ "Bold", "ed_format_bold.gif", false, function(e) {e.execCommand("bold");} ],
		italic:			[ "Italic", "ed_format_italic.gif", false, function(e) {e.execCommand("italic");} ],
		underline:		[ "Underline", "ed_format_underline.gif", false, function(e) {e.execCommand("underline");} ],
		strikethrough:	[ "Strikethrough", "ed_format_strike.gif", false, function(e) {e.execCommand("strikethrough");} ],
		subscript:		[ "Subscript", "ed_format_sub.gif", false, function(e) {e.execCommand("subscript");} ],
		superscript:	[ "Superscript", "ed_format_sup.gif", false, function(e) {e.execCommand("superscript");} ],
		justifyleft:	[ "Justify Left", "ed_align_left.gif", false, function(e) {e.execCommand("justifyleft");} ],
		justifycenter:	[ "Justify Center", "ed_align_center.gif", false, function(e) {e.execCommand("justifycenter");} ],
		justifyright:	[ "Justify Right", "ed_align_right.gif", false,	function(e) {e.execCommand("justifyright");} ],
		justifyfull:	[ "Justify Full", "ed_align_justify.gif", false, function(e) {e.execCommand("justifyfull");} ],
		orderedlist:	[ "Ordered List", "ed_list_num.gif", false, function(e) {e.execCommand("insertorderedlist");} ],
		unorderedlist:	[ "Bulleted List", "ed_list_bullet.gif", false,	function(e) {e.execCommand("insertunorderedlist");} ],
		outdent:		[ "Decrease Indent", "ed_indent_less.gif", false,	function(e) {e.execCommand("outdent");} ],
		indent:			[ "Increase Indent", "ed_indent_more.gif", false, function(e) {e.execCommand("indent");} ],
		forecolor:		[ "Font Color", "ed_color_fg.gif", false, function(e) {e.execCommand("forecolor");} ],
		hilitecolor:	[ "Background Color", "ed_color_bg.gif", false, function(e) {e.execCommand("hilitecolor");} ],
		inserthorizontalrule: 	[ "Horizontal Rule", "ed_hr.gif", false,	function(e) {e.execCommand("inserthorizontalrule");} ],
		createlink:		[ "Insert Web Link", "ed_link.gif", false, function(e) {e.execCommand("createlink", true);} ],
		insertimage:	[ "Insert/Modify Image", "ed_image.gif", false, function(e) {e.execCommand("insertimage");} ],
		inserttable:	[ "Insert Table", "insert_table.gif", false, function(e) {e.execCommand("inserttable");} ],
		toggletableborders: [ "Toggle table guidelines", "ed_table_borders.gif", false, function(e) {e.execCommand("toggletableborders");} ],
		htmlmode:		[ "Toggle HTML Source", "ed_html.gif", true, function(e) {e.execCommand("htmlmode");} ],
		popupeditor:	[ "Enlarge Editor", "fullscreen_maximize.gif", true, function(e) {e.execCommand("popupeditor");} ],
		about:			[ "About this editor", "ed_about.gif", true, function(e) {e.execCommand("about");} ],
		showhelp:		[ "Help using editor", "ed_help.gif", true, function(e) {e.execCommand("showhelp");} ],
		undo:			[ "Undoes your last action", "ed_undo.gif", false, function(e) {e.execCommand("undo");} ],
		redo:			[ "Redoes your last action", "ed_redo.gif", false, function(e) {e.execCommand("redo");} ],
		cut:			[ "Cut selection", "ed_cut.gif", false, cut_copy_paste ],
		copy:			[ "Copy selection", "ed_copy.gif", false, cut_copy_paste ],
		paste:			[ "Paste from clipboard", "ed_paste.gif", false, cut_copy_paste ],
		lefttoright:	[ "Direction left to right", "ed_left_to_right.gif", false, function(e) {e.execCommand("lefttoright");} ],
		righttoleft:	[ "Direction right to left", "ed_right_to_left.gif", false, function(e) {e.execCommand("righttoleft");} ],
		removeformat:	[ "Remove formatting", "ed_rmformat.gif", false, function(e) {e.execCommand("removeformat");} ],
		print:			[ "Print document", "ed_print.gif", false, function(e) {e._iframe.contentWindow.print(); } ],
		killword:		[ "Clear MSOffice tags", "ed_killword.gif", false, function(e) {e.execCommand("killword");	} ]
	};
	for ( var i in this.btnList) {
		var btn = this.btnList[i];
		btn[1] = _editor_url + this.imgURL + btn[1];
		if (typeof HTMLArea.I18N.tooltips[i] != "undefined") {
			if (/^(cut|copy|paste)$/.test(i) && HTMLArea.is_gecko
					&& HTMLArea.I18N.tooltips[i + '_ff'])
				i = i + '_ff';
			btn[0] = HTMLArea.I18N.tooltips[i];
		}
	}
};

HTMLArea.Config.prototype.registerButton = function(id, tooltip, image,
		textMode, action, context) {
	var the_id;
	if (typeof id == "string") {
		the_id = id;
	} else if (typeof id == "object") {
		the_id = id.id;
	} else {
		alert("ERROR [HTMLArea.Config::registerButton]:\ninvalid arguments");
		return false;
	}
	if (typeof this.customSelects[the_id] != "undefined") {
	}
	if (typeof this.btnList[the_id] != "undefined") {
	}
	switch (typeof id) {
	case "string":
		return this.btnList[id] = [ tooltip, image, textMode, action, context ];
	case "object":
		return this.btnList[id.id] = [ id.tooltip, id.image, id.textMode, id.action, id.context ];
	}
};

HTMLArea.Config.prototype.registerDropdown = function(object) {
	if (typeof this.customSelects[object.id] != "undefined") {
	}
	if (typeof this.btnList[object.id] != "undefined") {
	}
	this.customSelects[object.id] = object;
};

HTMLArea.Config.prototype.hideSomeButtons = function(remove) {
	var toolbar = this.toolbar;
	for (var i = toolbar.length; --i >= 0;) {
		var line = toolbar[i];
		for (var j = line.length; --j >= 0;) {
			if (remove.indexOf(" " + line[j] + " ") >= 0) {
				var len = 1;
				if (/separator|space/.test(line[j + 1])) {
					len = 2;
				}
				line.splice(j, len);
			}
		}
	}
};

HTMLArea.replaceAll = function(config) {
	var tas = document.getElementsByTagName("textarea");
	for (var i = tas.length; i > 0; (new HTMLArea(tas[--i], config)).generate())
		;
};

HTMLArea.replace = function(id, config) {
	var ta = HTMLArea.getElementById("textarea", id);
	return ta ? (new HTMLArea(ta, config)).generate() : null;
};

HTMLArea.prototype._createToolbar = function() {
	var editor = this;
	var toolbar = document.createElement("div");
	if (HTMLArea.is_ie)
		toolbar.style.width = "100%";
	HTMLArea._addEvents(toolbar, [ "mousedown", "mouseup" ], function(ev) {
		var tgt;
		if (HTMLArea.is_ie) {
			ev = window.event;
			tgt = ev.srcElement;
		} else
			tgt = ev.target;
		if (tgt === toolbar || tgt.disabled || tgt.parentNode.disabled) {
			editor.updateToolbar();
			HTMLArea._stopEvent(ev);
			return false;
		}
	});
	this._toolbar = toolbar;
	toolbar.className = "toolbar";
	toolbar.unselectable = "1";
	var tb_row = null;
	var tb_objects = new Object();
	this._toolbarObjects = tb_objects;
	function newLine() {
		var table = document.createElement("table");
		table.border = "0px";
		table.cellSpacing = "0px";
		table.cellPadding = "0px";
		toolbar.appendChild(table);
		var tb_body = document.createElement("tbody");
		table.appendChild(tb_body);
		tb_row = document.createElement("tr");
		tb_body.appendChild(tb_row);
	};
	newLine();
	function setButtonStatus(id, newval) {
		var oldval = this[id];
		var el = this.element;
		if (oldval != newval) {
			switch (id) {
			case "enabled":
				if (newval) {
					HTMLArea._removeClass(el, "buttonDisabled");
					el.disabled = false;
				} else {
					HTMLArea._addClass(el, "buttonDisabled");
					el.disabled = true;
				}
				break;
			case "active":
				if (newval) {
					HTMLArea._addClass(el, "buttonPressed");
				} else {
					HTMLArea._removeClass(el, "buttonPressed");
				}
				break;
			}
			this[id] = newval;
		}
	};
	function createSelect(txt) {
		var options = null;
		var el = null;
		var cmd = null;
		var customSelects = editor.config.customSelects;
		var context = null;
		var tooltip = "";
		switch (txt) {
		case "fontsize":
		case "fontname":
		case "formatblock":
			options = editor.config[txt];
			cmd = txt;
			break;
		default:
			cmd = txt;
			var dropdown = customSelects[cmd];
			if (typeof dropdown != "undefined") {
				options = dropdown.options;
				context = dropdown.context;
				if (typeof dropdown.tooltip != "undefined") {
					tooltip = dropdown.tooltip;
				}
			} else {
				alert("ERROR [createSelect]:\nCan't find the requested dropdown definition");
			}
			break;
		}
		if (options) {
			el = document.createElement("select");
			el.title = tooltip;
			var obj = {
				name : txt,
				element : el,
				enabled : true,
				text : false,
				cmd : cmd,
				state : setButtonStatus,
				context : context
			};
			tb_objects[txt] = obj;
			for ( var i in options) {
				var op = document.createElement("option");
				op.innerHTML = i;
				op.value = options[i];
				el.appendChild(op);
			}
			HTMLArea._addEvent(el, "change", function() {
				editor._comboSelected(el, txt);
			});
		}
		return el;
	};
	function createButton(txt) {
		var el = null;
		var btn = null;
		switch (txt) {
		case "separator":
			el = document.createElement("div");
			el.className = "separator";
			break;
		case "space":
			el = document.createElement("div");
			el.className = "space";
			break;
		case "linebreak":
			newLine();
			return false;
		case "textindicator":
			el = document.createElement("div");
			el.appendChild(document.createTextNode("A"));
			el.className = "indicator";
			el.title = HTMLArea.I18N.tooltips.textindicator;
			var obj = {
				name : txt,
				element : el,
				enabled : true,
				active : false,
				text : false,
				cmd : "textindicator",
				state : setButtonStatus
			};
			tb_objects[txt] = obj;
			break;
		default:
			btn = editor.config.btnList[txt];
		}
		if (!el && btn) {
			el = document.createElement("div");
			el.title = btn[0];
			el.className = "button";
			var obj = {
				name : txt,
				element : el,
				enabled : true,
				active : false,
				text : btn[2],
				cmd : btn[3],
				state : setButtonStatus,
				context : btn[4] || null
			};
			tb_objects[txt] = obj;
			HTMLArea._addEvent(el, "mouseover", function() {
				if (obj.enabled) {
					HTMLArea._addClass(el, "buttonHover");
				}
			});
			HTMLArea._addEvent(el, "mouseout", function() {
				if (obj.enabled)
					with (HTMLArea) {
						_removeClass(el, "buttonHover");
						_removeClass(el, "buttonActive");
						(obj.active) && _addClass(el, "buttonPressed");
					}
			});
			HTMLArea._addEvent(el, "mousedown", function(ev) {
				if (obj.enabled)
					with (HTMLArea) {
						_addClass(el, "buttonActive");
						_removeClass(el, "buttonPressed");
						_stopEvent(is_ie ? window.event : ev);
					}
			});
			HTMLArea._addEvent(el, "click", function(ev) {
				if (obj.enabled)
					with (HTMLArea) {
						_removeClass(el, "buttonActive");
						_removeClass(el, "buttonHover");
						obj.cmd(editor, obj.name, obj);
						_stopEvent(is_ie ? window.event : ev);
					}
			});
			var img = document.createElement("img");
			img.src = btn[1];
			img.style.width = "18px";
			img.style.height = "18px";
			if (HTMLArea.is_ie)
				img.ondragstart = HTMLArea.do_false;
			el.appendChild(img);
		} else if (!el) {
			el = createSelect(txt);
		}
		if (el) {
			var tb_cell = document.createElement("td");
			tb_row.appendChild(tb_cell);
			tb_cell.appendChild(el);
		} else {
			alert("FIXME: Unknown toolbar item: " + txt);
		}
		return el;
	};
	var first = true;
	for (var i = 0; i < this.config.toolbar.length; ++i) {
		if (!first) {
			createButton("linebreak");
		} else {
			first = false;
		}
		var group = this.config.toolbar[i];
		for (var j = 0; j < group.length; ++j) {
			var code = group[j];
			if (/^([IT])\[(.*?)\]/.test(code)) {
				var l7ed = RegExp.$1 == "I";
				var label = RegExp.$2;
				if (l7ed) {
					label = HTMLArea.I18N.custom[label];
				}
				var tb_cell = document.createElement("td");
				tb_row.appendChild(tb_cell);
				tb_cell.className = "label";
				tb_cell.innerHTML = label;
			} else {
				createButton(code);
			}
		}
	}
	this._htmlArea.appendChild(toolbar);
};

HTMLArea.prototype._createStatusBar = function() {
	var statusbar = document.createElement("div");
	statusbar.className = "statusBar";
	this._htmlArea.appendChild(statusbar);
	this._statusBar = statusbar;
	div = document.createElement("span");
	div.className = "statusBarTree";
	div.innerHTML = HTMLArea.I18N.msg["Path"] + ": ";
	this._statusBarTree = div;
	this._statusBar.appendChild(div);
	if (!this.config.statusBar) {
		statusbar.style.display = "none";
	}
};

HTMLArea.prototype.generate = function() {
	var editor = this;
	var textarea = this._textArea;
	if (typeof textarea == "string") {
		this._textArea = textarea = HTMLArea.getElementById("textarea",	textarea);
	}
	this._ta_size = {
		w : textarea.offsetWidth,
		h : textarea.offsetHeight
	};
	textarea.style.display = "none";
	var htmlarea = document.createElement("div");
	htmlarea.className = "htmlarea";
	this._htmlArea = htmlarea;
	textarea.parentNode.insertBefore(htmlarea, textarea);
	if (textarea.form) {
		var f = textarea.form;
		if (typeof f.onsubmit == "function") {
			var funcref = f.onsubmit;
			if (typeof f.__msh_prevOnSubmit == "undefined") {
				f.__msh_prevOnSubmit = [];
			}
			f.__msh_prevOnSubmit.push(funcref);
		}
		f.onsubmit = function() {
			editor._textArea.value = editor.getHTML();
			var a = this.__msh_prevOnSubmit;
			if (typeof a != "undefined") {
				for (var i = a.length; --i >= 0;) {
					a[i]();
				}
			}
		};
		if (typeof f.onreset == "function") {
			var funcref = f.onreset;
			if (typeof f.__msh_prevOnReset == "undefined") {
				f.__msh_prevOnReset = [];
			}
			f.__msh_prevOnReset.push(funcref);
		}
		f.onreset = function() {
			editor.setHTML(editor._textArea.value);
			editor.updateToolbar();
			var a = this.__msh_prevOnReset;
			if (typeof a != "undefined") {
				for (var i = a.length; --i >= 0;) {
					a[i]();
				}
			}
		};
	}
	try {
		window.onunload = function() {
			editor._textArea.value = editor.getHTML();
		};
	} catch (e) {};
	
	this._createToolbar();
	htmlarea.appendChild(textarea);
	var iframe = document.createElement("iframe");
	iframe.src = HTMLArea.is_ie ? "javascript:false;" : "about:blank";
	htmlarea.appendChild(iframe);
	this._iframe = iframe;
	this._createStatusBar();
	if (!HTMLArea.is_ie) {
		iframe.style.borderWidth = "0";
	}
	var height = this.config.height, width = this.config.width;
	if (height == "auto")
		height = this._ta_size.h;
	if (height == parseInt(height, 10)) {
		if (this.config.sizeIncludesToolbar) {
			height -= this._toolbar.offsetHeight;
			height -= this._statusBar.offsetHeight;
		}
		if (height < 0)
			height = 0;
		height += "px";
	}
	if (width == "auto")
		width = this._ta_size.w;
	if (width == parseInt(width, 10))
		width += "px";
	iframe.style.width = width;
	iframe.style.height = height;
	textarea.style.width = width;
	textarea.style.height = height;
	function initIframe() {
		var doc = editor._iframe.contentWindow.document;
		if (!doc) {
			if (HTMLArea.is_gecko) {
				setTimeout(initIframe, 100);
				return false;
			} else {
				alert("ERROR: IFRAME can't be initialized.");
			}
		}
		if (HTMLArea.is_midas) {
			doc.designMode = "on";
		}
		editor._doc = doc;
		if (!editor.config.fullPage) {
			doc.open();
			var html = '<!doctype html>'
					+ "<html>\n";
			html += "<head>\n";
			if (editor.config.baseURL)
				html += '<base href="' + editor.config.baseURL + '" />';
			html += "<link rel='stylesheet' href='" + _editor_url
					+ "iframe.css' />";
			html += "<style type='text/css'>" + editor.config.pageStyle
					+ " html,body { border: 0px; }</style>\n";
			html += "</head>\n";
			html += "<body class='msh_htmlarea'>\n";
			html += editor._textArea.value;
			html += "</body>\n";
			html += "</html>";
			doc.write(html);
			doc.close();
		} else {
			var html = editor._textArea.value;
			if (html.match(HTMLArea.RE_doctype)) {
				editor.setDoctype(RegExp.$1);
				html = html.replace(HTMLArea.RE_doctype, "");
			}
			doc.open();
			doc.write(html);
			doc.close();
		}
		if (HTMLArea.is_ie) {
			doc.body.contentEditable = true;
		}
		HTMLArea._addEvents(
			doc,
			[ "keydown", "keypress", "mousedown", "mouseup", "drag" ],
			function(event) {
				return editor._editorEvent(HTMLArea.is_ie ? editor._iframe.contentWindow.event : event);
		});
		for ( var i in editor.plugins) {
			var plugin = editor.plugins[i].instance;
			if (typeof plugin.onGenerate == "function")
				plugin.onGenerate();
			if (typeof plugin.onGenerateOnce == "function") {
				plugin.onGenerateOnce();
				plugin.onGenerateOnce = null;
			}
		}
		var timer = setInterval(function() {
			if (editor._doc.body) {
				if (/\S/.test(editor._textArea.value)) {
					editor.convertSemanticTags(true);
					editor._wordClean();
				} else
					editor.execCommand("formatblock", false, "p");
				editor.updateToolbar();
				window.focus();
				editor.focusEditor();
				clearInterval(timer);
			}
		}, 100);
		if (typeof editor.onGenerate == "function")
			editor.onGenerate();
	}
	;
	setTimeout(initIframe, 100);
};

HTMLArea.prototype.setMode = function(mode) {
	if (typeof mode == "undefined") {
		mode = ((this._editMode == "textmode") ? "wysiwyg" : "textmode");
	}
	switch (mode) {
	case "textmode":
		this._textArea.value = this.getHTML();
		this._iframe.style.display = "none";
		this._textArea.style.display = "block";
		if (this.config.statusBar) {
			this._statusBar.innerHTML = HTMLArea.I18N.msg["TEXT_MODE"];
		}
		break;
	case "wysiwyg":
		if (HTMLArea.is_midas) {
			try {
				this._doc.designMode = "off";
			} catch (e) {
			}
			;
		}
		if (!this.config.fullPage)
			this._doc.body.innerHTML = this.getHTML();
		else
			this.setFullHTML(this.getHTML());
		this._iframe.style.display = "block";
		this._textArea.style.display = "none";
		if (HTMLArea.is_midas) {
			try {
				this._doc.designMode = "on";
			} catch (e) {
			}
			;
		}
		if (this.config.statusBar) {
			this._statusBar.innerHTML = '';
			this._statusBar.appendChild(this._statusBarTree);
		}
		this.convertSemanticTags(true);
		break;
	default:
		alert("Mode <" + mode + "> not defined!");
		return false;
	}
	this._editMode = mode;
	this.focusEditor();
	for ( var i in this.plugins) {
		var plugin = this.plugins[i].instance;
		if (typeof plugin.onMode == "function")
			plugin.onMode(mode);
	}
};

HTMLArea.prototype.setFullHTML = function(html) {
	var save_multiline = RegExp.multiline;
	RegExp.multiline = true;
	if (html.match(HTMLArea.RE_doctype)) {
		this.setDoctype(RegExp.$1);
		html = html.replace(HTMLArea.RE_doctype, "");
	}
	RegExp.multiline = save_multiline;
	if (!HTMLArea.is_ie) {
		if (html.match(HTMLArea.RE_head))
			this._doc.getElementsByTagName("head")[0].innerHTML = RegExp.$1;
		if (html.match(HTMLArea.RE_body))
			this._doc.getElementsByTagName("body")[0].innerHTML = RegExp.$1;
	} else {
		var html_re = /<html>((.|\n)*?)<\/html>/i;
		html = html.replace(html_re, "$1");
		this._doc.open();
		this._doc.write(html);
		this._doc.close();
		this._doc.body.contentEditable = true;
		return true;
	}
};

HTMLArea.prototype.registerPlugin = function() {
	var plugin = arguments[0];
	var args = [];
	for (var i = 1; i < arguments.length; ++i)
		args.push(arguments[i]);
	this.registerPlugin2(plugin, args);
};

HTMLArea.prototype.registerPlugin2 = function(plugin, args) {
	if (typeof plugin == "string")
		plugin = eval(plugin);
	if (typeof plugin == "undefined") {
		return false;
	}
	var obj = new plugin(this, args);
	if (obj) {
		var clone = {};
		var info = plugin._pluginInfo;
		for ( var i in info)
			clone[i] = info[i];
		clone.instance = obj;
		clone.args = args;
		this.plugins[plugin._pluginInfo.name] = clone;
	} else
		alert("Can't register plugin " + plugin.toString() + ".");
};

HTMLArea.getPluginDir = function(pluginName) {
	return _editor_url + "plugins/" + pluginName;
};

HTMLArea.loadPlugin = function(pluginName) {
	var dir = this.getPluginDir(pluginName);
	var plugin = pluginName.replace(/([a-z])([A-Z])([a-z])/g,
			function(str, l1, l2, l3) {
				return l1 + "-" + l2.toLowerCase() + l3;
			}).toLowerCase()
			+ ".js";
	var plugin_file = dir + "/" + plugin;
	var plugin_lang = dir + "/lang/" + _editor_lang + ".js";
	this.loadScript(plugin_file);
	this.loadScript(plugin_lang);
};

HTMLArea.loadStyle = function(style, plugin) {
	var url = _editor_url || '';
	if (typeof plugin != "undefined") {
		url += "plugins/" + plugin + "/";
	}
	url += style;
	if (/^\x2f/.test(style))
		url = style;
	var head = document.getElementsByTagName("head")[0];
	var link = document.createElement("link");
	link.rel = "stylesheet";
	link.href = url;
	head.appendChild(link);
};

HTMLArea.loadStyle(typeof _editor_css == "string" ? _editor_css	: "htmlarea.css");

HTMLArea.prototype.debugTree = function() {
	var ta = document.createElement("textarea");
	ta.style.width = "100%";
	ta.style.height = "20em";
	ta.value = "";
	function debug(indent, str) {
		for (; --indent >= 0;)
			ta.value += " ";
		ta.value += str + "\n";
	}
	;
	function _dt(root, level) {
		var tag = root.tagName.toLowerCase(), i;
		var ns = HTMLArea.is_ie ? root.scopeName : root.prefix;
		debug(level, "- " + tag + " [" + ns + "]");
		for (i = root.firstChild; i; i = i.nextSibling)
			if (i.nodeType == 1)
				_dt(i, level + 2);
	}
	;
	_dt(this._doc.body, 0);
	document.body.appendChild(ta);
};

HTMLArea.getInnerText = function(el) {
	var txt = '', i;
	for (i = el.firstChild; i; i = i.nextSibling) {
		if (i.nodeType == 3)
			txt += i.data;
		else if (i.nodeType == 1)
			txt += HTMLArea.getInnerText(i);
	}
	return txt;
};

HTMLArea.prototype.collapsed = function() {
	var sel = this._getSelection(), range = this._createRange(sel);
	if (HTMLArea.is_ie)
		return range.compareEndPoints("StartToEnd", range) == 0;
	else
		return range.collapsed;
};

HTMLArea.prototype.flatRange = function() {
	var df, sel = this._getSelection(), range = this._createRange(sel), tmp;
	if (HTMLArea.is_ie) {
		if (sel.type == "Control")
			return false;
		try {
			tmp = document.createElement("div");
			tmp.innerHTML = range.htmlText;
		} catch (e) {
			return false;
		}
		if (tmp.childNodes.length > 1)
			return false;
	} else
		try {
			df = range.cloneContents();
			if (range.startContainer.nodeType == 1
					&& range.endContainer.nodeType == 1)
				df = df.firstChild;
			if (df) {
				if (/^img$/i.test(df.tagName) && !df.nextSibling)
					return false;
				while (df.firstChild)
					if (df.removeChild(df.firstChild).nodeType == 1)
						return false;
			}
		} catch (e) {
		}
	;
	return true;
};

HTMLArea.prototype._wordClean = function(want_stats) {
	var body = this._doc.body;
	var stats = {
		empty_tags : 0,
		mso_class : 0,
		mso_style : 0,
		mso_xmlel : 0,
		orig_len : body.innerHTML.length,
		T : (new Date()).getTime()
	}, stats_txt = {
		empty_tags : "Empty tags removed: ",
		mso_class : "MSO class names removed: ",
		mso_style : "MSO inline style removed: ",
		mso_xmlel : "MSO XML elements stripped: "
	};
	function showStats() {
		var txt = "Word cleaner stats: \n\n";
		for ( var i in stats)
			if (stats_txt[i])
				txt += stats_txt[i] + stats[i] + "\n";
		txt += "\nInitial document length: " + stats.orig_len + "\n";
		txt += "Final document length: " + body.innerHTML.length + "\n";
		txt += "Clean-up took " + (((new Date()).getTime() - stats.T) / 1000)
				+ " seconds";
		alert(txt);
	}
	;
	function clearClass(node) {
		var newc = node.className.replace(/(^|\s)mso.*?(\s|$)/ig, ' ');
		if (newc != node.className) {
			node.className = newc;
			if (!/\S/.test(node.className)) {
				node.removeAttribute("className");
				++stats.mso_class;
			}
		}
	}
	;
	function clearStyle(node) {
		var declarations = node.style.cssText.split(/\s*;\s*/), line;
		for (var i = declarations.length; --i >= 0;) {
			line = declarations[i];
			if (/^mso|^tab-stops|^layout-grid/i.test(line)
					|| /^margin\s*:\s*[0-9]+..\s+[0-9]+..\s+[0-9]+../i
							.test(line)) {
				++stats.mso_style;
				declarations.splice(i, 1);
			} else {
				declarations[i] = line.replace(/windowtext/, '#000');
			}
		}
		node.style.cssText = declarations.join("; ");
	}
	;
	function stripTag(el) {
		if (HTMLArea.is_ie)
			el.outerHTML = HTMLArea.htmlEncode(el.innerText);
		else {
			var txt = document.createTextNode(HTMLArea.getInnerText(el));
			el.parentNode.insertBefore(txt, el);
			el.parentNode.removeChild(el);
		}
		++stats.mso_xmlel;
	}
	;
	function checkEmpty(el) {
		if (/^(a|span|b|strong|i|em|font)$/i.test(el.tagName)
				&& el.childNodes.length == 0) {
			el.parentNode.removeChild(el);
			++stats.empty_tags;
		}
	}
	;
	function parseTree(root) {
		var tag = root.tagName.toLowerCase(), i, next;
		if ((HTMLArea.is_ie && root.scopeName != 'HTML')
				|| (!HTMLArea.is_ie && /:/.test(tag))) {
			stripTag(root);
			return false;
		} else {
			clearClass(root);
			clearStyle(root);
			for (i = root.firstChild; i; i = next) {
				next = i.nextSibling;
				if (i.nodeType == 1 && parseTree(i))
					checkEmpty(i);
			}
		}
		return true;
	}
	;
	parseTree(body);
	this.updateToolbar();
};

HTMLArea.prototype.forceRedraw = function() {
	this._doc.body.style.visibility = "hidden";
	this._doc.body.style.visibility = "visible";
};

HTMLArea.prototype.focusEditor = function() {
	switch (this._editMode) {
	case "wysiwyg":
		try {
			this._iframe.contentWindow.focus();
		} catch (e) {
		}
		break;
	case "textmode":
		try {
			this._textArea.focus();
		} catch (e) {
		}
		break;
	default:
		alert("ERROR: mode " + this._editMode + " is not defined");
	}
	return this._doc;
};

HTMLArea.prototype._undoTakeSnapshot = function() {
	++this._undoPos;
	if (this._undoPos >= this.config.undoSteps) {
		this._undoQueue.shift();
		--this._undoPos;
	}
	var take = true;
	var txt = this.getInnerHTML();
	if (this._undoPos > 0)
		take = (this._undoQueue[this._undoPos - 1] != txt);
	if (take) {
		this._undoQueue[this._undoPos] = txt;
	} else {
		this._undoPos--;
	}
};

HTMLArea.prototype.undo = function() {
	if (this._undoPos > 0) {
		var txt = this._undoQueue[--this._undoPos];
		if (txt)
			this.setHTML(txt);
		else
			++this._undoPos;
	}
};

HTMLArea.prototype.redo = function() {
	if (this._undoPos < this._undoQueue.length - 1) {
		var txt = this._undoQueue[++this._undoPos];
		if (txt)
			this.setHTML(txt);
		else
			--this._undoPos;
	}
};

HTMLArea.prototype.resetStatusBar = function(txt) {
	if (this.config.statusBar) {
		var b = this._statusBarTree;
		while (b.firstChild)
			b.removeChild(b.lastChild);
		if (!txt)
			txt = '-';
		b.appendChild(document.createTextNode(txt));
	}
};

HTMLArea.prototype.updateToolbar = function(noStatus) {
	var doc = this._doc;
	var text = (this._editMode == "textmode");
	var ancestors = null;
	if (!text) {
		ancestors = this.getAllAncestors();
		if (this.config.statusBar && !noStatus) {
			this.resetStatusBar(HTMLArea.I18N.msg["Path"] + ": ");
			for (var i = ancestors.length; --i >= 0;) {
				var el = ancestors[i];
				if (!el) {
					continue;
				}
				var a = document.createElement("a");
				a.href = "#";
				a.el = el;
				a.editor = this;
				a.onclick = function() {
					this.blur();
					this.editor.selectNodeContents(this.el);
					this.editor.updateToolbar(true);
					return false;
				};
				a.ondblclick = function() {
					this.blur();
					this.editor.selectNodeContents(this.el, null, true);
					this.editor.updateToolbar(true);
					return false;
				};
				a.oncontextmenu = function() {
					this.blur();
					var info = "Inline style:\n\n";
					info += this.el.style.cssText.split(/;\s*/).join(";\n");
					alert(info);
					return false;
				};
				var txt = el.tagName.toLowerCase();
				a.title = el.style.cssText;
				if (el.id) {
					txt += "#" + el.id;
				}
				var tmp = el.className.replace(/\s*_msh_[^\s]+\s*/, ' ');
				if (/\S/.test(tmp))
					txt += "." + tmp;
				a.appendChild(document.createTextNode(txt));
				this._statusBarTree.appendChild(a);
				if (i != 0) {
					this._statusBarTree.appendChild(document
							.createTextNode(String.fromCharCode(0xbb)));
				}
			}
		}
	}
	for ( var i in this._toolbarObjects) {
		var btn = this._toolbarObjects[i];
		var cmd = i;
		var inContext = true;
		if (btn.context && !text) {
			inContext = false;
			var context = btn.context;
			var attrs = [];
			if (/(.*)\[(.*?)\]/.test(context)) {
				context = RegExp.$1;
				attrs = RegExp.$2.split(",");
			}
			context = context.toLowerCase();
			var match = (context == "*");
			for (var k = 0; k < ancestors.length; ++k) {
				if (!ancestors[k]) {
					continue;
				}
				if (match || (ancestors[k].tagName.toLowerCase() == context)) {
					inContext = true;
					for (var ka = 0; ka < attrs.length; ++ka) {
						if (!eval("ancestors[k]." + attrs[ka])) {
							inContext = false;
							break;
						}
					}
					if (inContext) {
						break;
					}
				}
			}
		}
		btn.state("enabled", (!text || btn.text) && inContext);
		if (typeof cmd == "function") {
			continue;
		}
		var dropdown = this.config.customSelects[cmd];
		if ((!text || btn.text) && (typeof dropdown != "undefined")) {
			dropdown.refresh(this);
			continue;
		}
		switch (cmd) {
		case "fontname":
		case "fontsize":
		case "formatblock":
			if (!text)
				try {
					var value = ("" + doc.queryCommandValue(cmd)).toLowerCase();
					if (!value) {
						btn.element.selectedIndex = 0;
						break;
					}
					var options = this.config[cmd];
					var k = 0;
					for ( var j in options) {
						if ((j.toLowerCase() == value)
								|| (options[j].substr(0, value.length)
										.toLowerCase() == value)) {
							btn.element.selectedIndex = k;
							throw "ok";
						}
						++k;
					}
					btn.element.selectedIndex = 0;
				} catch (e) {
				}
			;
			break;
		case "textindicator":
			if (!text) {
				try {
					with (btn.element.style) {
						backgroundColor = HTMLArea._makeColor(doc
								.queryCommandValue(HTMLArea.is_ie ? "backcolor"
										: "hilitecolor"));
						if (/transparent/i.test(backgroundColor)) {
							backgroundColor = HTMLArea._makeColor(doc
									.queryCommandValue("backcolor"));
						}
						color = HTMLArea._makeColor(doc
								.queryCommandValue("forecolor"));
						fontFamily = doc.queryCommandValue("fontname");
						fontWeight = doc.queryCommandState("bold") ? "bold"
								: "normal";
						fontStyle = doc.queryCommandState("italic") ? "italic"
								: "normal";
					}
				} catch (e) {
				}
			}
			break;
		case "htmlmode":
			btn.state("active", text);
			break;
		case "lefttoright":
		case "righttoleft":
			var el = this.getParentElement();
			while (el && !HTMLArea.isBlockElement(el))
				el = el.parentNode;
			if (el)
				btn.state("active",
						(el.style.direction == ((cmd == "righttoleft") ? "rtl"
								: "ltr")));
			break;
		case "toggletableborders":
			btn.state("active", this.css_tableBorders
					&& !this.css_tableBorders.disabled);
			break;
		default:
			cmd = cmd.replace(/(un)?orderedlist/i, "insert$1orderedlist");
			try {
				btn.state("active", (!text && doc.queryCommandState(cmd)));
			} catch (e) {
			}
		}
	}
	if (this._customUndo && !this._timerUndo) {
		this._undoTakeSnapshot();
		var editor = this;
		this._timerUndo = setTimeout(function() {
			editor._timerUndo = null;
		}, this.config.undoTimeout);
	}
	for ( var i in this.plugins) {
		var plugin = this.plugins[i].instance;
		if (typeof plugin.onUpdateToolbar == "function")
			plugin.onUpdateToolbar();
	}
	if (!HTMLArea.is_ie)
		this.forceRedraw();
};

HTMLArea.prototype.insertNodeAtSelection = function(toBeInserted) {
	if (!HTMLArea.is_ie) {
		var sel = this._getSelection();
		var range = this._createRange(sel);
		sel.removeAllRanges();
		range.deleteContents();
		range.insertNode(toBeInserted);
		range.selectNodeContents(toBeInserted);
		this.updateToolbar();
	} else {
		return null;
	}
};

HTMLArea.prototype.getParentElement = function() {
	var sel = this._getSelection();
	var range = this._createRange(sel);
	if (HTMLArea.is_ie) {
		switch (sel.type) {
		case "Text":
		case "None":
			return range.parentElement();
		case "Control":
			return range.item(0);
		default:
			return this._doc.body;
		}
	} else
		try {
			var p = range.commonAncestorContainer;
			if (!range.collapsed && range.startContainer == range.endContainer
					&& range.startOffset - range.endOffset <= 1
					&& range.startContainer.hasChildNodes())
				p = range.startContainer.childNodes[range.startOffset];
			while (p.nodeType == 3) {
				p = p.parentNode;
			}
			return p;
		} catch (e) {
			return null;
		}
};

HTMLArea.prototype.getAllAncestors = function() {
	var p = this.getParentElement();
	if (p && p.nodeType == 1) {
		if (HTMLArea.is_gecko)
			this.convertSemanticTags(true, p);
		p = this.getParentElement();
	}
	var a = [];
	while (p && (p.nodeType == 1) && (p.tagName.toLowerCase() != 'body')) {
		a.push(p);
		p = p.parentNode;
	}
	a.push(this._doc.body);
	return a;
};

HTMLArea.prototype.getAncestorsHash = function() {
	var p = this.getAllAncestors(), el, i, tn, pnodes = {};
	try {
		for (i = 0; i < p.length; ++i) {
			el = p[i];
			tn = el.tagName.toLowerCase();
			if (!pnodes[tn])
				pnodes[tn] = el;
		}
	} catch (e) {
	}
	;
	return pnodes;
};

HTMLArea.prototype.selectNodeContents = function(node, pos, inclusive) {
	this.focusEditor();
	this.forceRedraw();
	var range;
	var collapsed = (typeof pos == "boolean");
	if (HTMLArea.is_ie) {
		range = this._doc.body.createTextRange();
		range.moveToElementText(node);
		(collapsed) && range.collapse(pos);
		range.select();
	} else {
		var sel = this._getSelection();
		range = this._doc.createRange();
		if (inclusive)
			range.selectNode(node);
		else
			range.selectNodeContents(node);
		(collapsed) && range.collapse(pos);
		sel.removeAllRanges();
		sel.addRange(range);
	}
};

HTMLArea.prototype.insertHTML = function(html) {
	var sel = this._getSelection();
	var range = this._createRange(sel);
	if (HTMLArea.is_ie) {
		range.pasteHTML(html);
	} else {
		var fragment = this._doc.createDocumentFragment();
		var div = this._doc.createElement("div");
		div.innerHTML = html;
		while (div.firstChild) {
			fragment.appendChild(div.firstChild);
		}
		var node = this.insertNodeAtSelection(fragment);
	}
};

HTMLArea.prototype.surroundHTML = function(startTag, endTag) {
	var html = this.getSelectedHTML();
	this.insertHTML(startTag + html + endTag);
};

HTMLArea.prototype.getSelectedHTML = function() {
	var sel = this._getSelection();
	var range = this._createRange(sel);
	var existing = null;
	if (HTMLArea.is_ie) {
		existing = range.htmlText;
	} else {
		existing = HTMLArea.getHTML(range.cloneContents(), false, this);
	}
	return existing;
};

HTMLArea.prototype.hasSelectedText = function() {
	return this.getSelectedHTML() != '';
};

HTMLArea.prototype._makeLink = function(url) {
	var sel, range;
	this._doc.execCommand("createlink", false, url);
	sel = this._getSelection();
	range = this._createRange(sel);
	if (HTMLArea.is_ie) {
		if (sel.type == "Text") {
			range.moveEnd("character", -1);
			link = range.parentElement();
		} else
			link = range.item(0).parentNode;
	} else {
		link = range.startContainer;
		if (!/^a$/i.test(link.tagName)) {
			link = link.nextSibling;
			if (!link)
				link = range.startContainer.parentNode;
		}
	}
	return link;
};

HTMLArea.prototype.createAnchor = function(name) {
	var a = this._makeLink("#");
	if (a && a.tagName.toLowerCase() == "a") {
		a.removeAttribute("href");
		a.setAttribute("name", name);
		a.setAttribute("id", name);
		a.className = "htmlarea-anchor";
	}
};

HTMLArea.prototype._createLink = function(link) {
	var editor = this;
	var outparam = null;
	if (typeof link == "undefined")
		link = this.getAncestorsHash().a;
	if (!link) {
		var sel = editor._getSelection();
		var range = editor._createRange(sel);
		var compare = 0;
		if (HTMLArea.is_ie) {
			if (sel.type == "Text")
				compare = range.compareEndPoints("StartToEnd", range);
			else if (sel.type == "Control")
				compare = (range.length == 1);
		} else {
			compare = range.compareBoundaryPoints(range.START_TO_END, range);
		}
		if (compare == 0) {
			alert(HTMLArea.I18N.msg["Link-Text"]);
			return;
		}
		outparam = {
			f_href : '',
			f_title : '',
			f_target : '',
			f_usetarget : editor.config.makeLinkShowsTarget
		};
	} else
		outparam = {
			f_href : HTMLArea.is_ie ? editor.stripBaseURL(link.href) : link
					.getAttribute("href"),
			f_title : link.title,
			f_target : link.target,
			f_usetarget : editor.config.makeLinkShowsTarget
		};
	this._popupDialog("link.html", function(param) {
		if (!param)
			return false;
		var a = link;
		if (!a)
			a = editor._makeLink(param.f_href);
		else {
			var href = param.f_href.trim();
			editor.selectNodeContents(a);
			if (href == "") {
				editor._doc.execCommand("unlink", false, null);
				editor.updateToolbar();
				return false;
			} else
				a.href = href;
		}
		if (!(a && /^a$/i.test(a.tagName)))
			return false;
		a.target = param.f_target.trim();
		a.title = param.f_title.trim();
		editor.selectNodeContents(a);
		editor.updateToolbar();
	}, outparam);
};

HTMLArea.prototype._insertImage = function(image) {
	var editor = this;
	var outparam = null;
	if (typeof image == "undefined") {
		image = this.getParentElement();
		if (image && !/^img$/i.test(image.tagName))
			image = null;
	}
	if (image)
		outparam = {
			f_base : editor.config.baseURL,
			f_url : HTMLArea.is_ie ? editor.stripBaseURL(image.src) : image.getAttribute("src"),
			f_alt : image.alt,
			f_border : image.border,
			f_align : image.align,
			f_vert : image.vspace,
			f_horiz : image.hspace
		};
	this._popupDialog("insert_image.html", function(param) {
		if (!param) {
			return false;
		}
		var img = image;
		if (!img) {
			var sel = editor._getSelection();
			var range = editor._createRange(sel);
			editor._doc.execCommand("insertimage", false, param.f_url);
			if (HTMLArea.is_ie) {
				img = range.parentElement();
				if (img.tagName.toLowerCase() != "img") {
					img = img.previousSibling;
				}
			} else {
				img = range.startContainer.previousSibling;
			}
		} else {
			img.src = param.f_url;
		}
		for ( var field in param) {
			var value = param[field];
			switch (field) {
			case "f_alt":
				img.alt = value;
				break;
			case "f_border":
				img.border = parseInt(value || "0");
				break;
			case "f_align":
				img.align = value;
				break;
			case "f_vert":
				img.vspace = parseInt(value || "0");
				break;
			case "f_horiz":
				img.hspace = parseInt(value || "0");
				break;
			}
		}
	}, outparam);
};

HTMLArea.prototype._insertTable = function() {
	var sel = this._getSelection();
	var range = this._createRange(sel);
	var editor = this;
	this._popupDialog("insert_table.html", function(param) {
		if (!param) {
			return false;
		}
		var doc = editor._doc;
		var table = doc.createElement("table");
		table.style.borderCollapse = "collapse";
		for ( var field in param) {
			var value = param[field];
			if (!value) {
				continue;
			}
			switch (field) {
			case "f_width":
				table.style.width = value + param["f_unit"];
				break;
			case "f_align":
				table.align = value;
				break;
			case "f_border":
				table.style.border = parseInt(value) + "px solid #000";
				break;
			case "f_spacing":
				table.cellSpacing = parseInt(value);
				break;
			case "f_padding":
				table.cellPadding = parseInt(value);
				break;
			}
		}
		var cellwidth = 0;
		if (param.f_fixed)
			cellwidth = Math.floor(100 / parseInt(param.f_cols));
		var tbody = doc.createElement("tbody");
		table.appendChild(tbody);
		for (var i = 0; i < param["f_rows"]; ++i) {
			var tr = doc.createElement("tr");
			tbody.appendChild(tr);
			for (var j = 0; j < param["f_cols"]; ++j) {
				var td = doc.createElement("td");
				td.style.border = table.style.border;
				if (cellwidth)
					td.style.width = cellwidth + "%";
				tr.appendChild(td);
				(HTMLArea.is_midas) && td.appendChild(doc.createElement("br"));
			}
		}
		if (HTMLArea.is_ie) {
			range.pasteHTML(table.outerHTML);
		} else {
			editor.insertNodeAtSelection(table);
		}
		return true;
	}, null);
};

HTMLArea.prototype._comboSelected = function(el, txt) {
	this.focusEditor();
	var value = el.options[el.selectedIndex].value, tmp;
	switch (txt) {
	case "fontname":
	case "fontsize":
	case "formatblock":
		this.execCommand(txt, false, value);
		break;
	default:
		var dropdown = this.config.customSelects[txt];
		if (typeof dropdown != "undefined") {
			dropdown.action(this);
		} else {
			alert("FIXME: combo box " + txt + " not implemented");
		}
	}
};

HTMLArea.prototype.toggleTableBorders = function() {
	var doc = this._doc, head = doc.getElementsByTagName("head")[0], css = this.css_tableBorders;
	if (!css) {
		this.css_tableBorders = css = doc.createElement("link");
		css.type = "text/css";
		css.media = "all";
		css.rel = "stylesheet";
		css.href = _editor_url + 'table-borders.css';
		head.appendChild(css);
	} else
		css.disabled = !css.disabled;
};

HTMLArea.prototype.execCommand = function(cmdID, UI, param) {
	var editor = this;
	this.focusEditor();
	cmdID = cmdID.toLowerCase();
	var styleCmd = /^(justify.*|lefttoright|righttoleft|.*color)$/.test(cmdID);
	if (HTMLArea.is_gecko)
		try {
			this._doc.execCommand('useCSS', false, !styleCmd);
		} catch (e) {
		}
	;
	switch (cmdID) {
	case "toggletableborders":
		this.toggleTableBorders();
		break;
	case "htmlmode":
		this.setMode();
		break;
	case "hilitecolor":
		(HTMLArea.is_ie) && (cmdID = "backcolor");
	case "forecolor":
		this._popupDialog("select_color.html", function(color) {
			if (color) {
				editor._doc.execCommand(cmdID, false, "#" + color);
			}
		}, HTMLArea._colorToRgb(this._doc.queryCommandValue(cmdID)));
		break;
	case "createlink":
		this._createLink();
		break;
	case "popupeditor":
		HTMLArea._object = this;
		if (HTMLArea.is_ie) {
			window.open(this.popupURL("fullscreen.html"), "ha_fullscreen",
					"toolbar=no,location=no,directories=no,status=no,menubar=no,"
					+ "scrollbars=no,resizable=yes,width=640,height=480");
		} else {
			window.open(this.popupURL("fullscreen.html"), "ha_fullscreen",
					"toolbar=no,menubar=no,personalbar=no,width=640,height=480,"
					+ "scrollbars=no,resizable=yes");
		}
		break;
	case "undo":
	case "redo":
		try {
			if (this._customUndo)
				this[cmdID]();
			else
				this._doc.execCommand(cmdID, UI, param);
		} catch (e) {
		}
		;
		break;
	case "inserttable":
		this._insertTable();
		break;
	case "insertimage":
		this._insertImage();
		break;
	case "about":
		this._popupDialog("about.html", null, this);
		break;
	case "showhelp":
		window.open(_editor_url + "reference.html", "ha_help");
		break;
	case "killword":
		this._wordClean(true);
		break;
	case "formatblock":
		if (HTMLArea.is_ie)
			param = "<" + param + ">";
		this._doc.execCommand(cmdID, UI, param);
		break;
	case "cut":
	case "copy":
	case "paste":
		try {
			this._doc.execCommand(cmdID, UI, param);
			if (this.config.killWordOnPaste)
				this._wordClean();
		} catch (e) {
			if (HTMLArea.is_gecko) {
				if (typeof HTMLArea.I18N.msg["Moz-Clipboard"] == "undefined") {
					HTMLArea.I18N.msg["Moz-Clipboard"] = "Unprivileged scripts cannot access Cut/Copy/Paste programatically "
							+ "for security reasons. Click OK to see a technical note at mozilla.org "
							+ "which shows you how to allow a script to access the clipboard.\n\n"
							+ "[FIXME: please translate this message in your language definition file.]";
				}
				if (confirm(HTMLArea.I18N.msg["Moz-Clipboard"]))
					window.open("https://developer.mozilla.org/en-US/docs/Midas/Security_preferences");
			}
		}
		break;
	case "lefttoright":
	case "righttoleft":
		var dir = (cmdID == "righttoleft") ? "rtl" : "ltr";
		var el = this.getParentElement();
		while (el && !HTMLArea.isBlockElement(el))
			el = el.parentNode;
		if (el) {
			if (el.style.direction == dir)
				el.style.direction = "";
			else
				el.style.direction = dir;
		}
		break;
	default:
		try {
			this._doc.execCommand(cmdID, UI, param);
		} catch (e) {
			if (this.config.debug) {
				alert(e + "\n\nby execCommand(" + cmdID + ");");
			}
		}
	}
	this.updateToolbar();
	return false;
};

HTMLArea.prototype._editorEvent = function(ev) {
	var editor = this;
	var keyEvent = (HTMLArea.is_ie && ev.type == "keydown")
			|| (!HTMLArea.is_ie && ev.type == "keypress");
	if (keyEvent)
		for (var i in editor.plugins) {
			var plugin = editor.plugins[i].instance;
			if (typeof plugin.onKeyPress == "function")
				if (plugin.onKeyPress(ev))
					return false;
		}
	if (keyEvent && ev.ctrlKey && !ev.altKey) {
		var sel = null;
		var range = null;
		var key = String
				.fromCharCode(HTMLArea.is_ie ? ev.keyCode : ev.charCode)
				.toLowerCase();
		var cmd = null;
		var value = null;
		switch (key) {
		case 'a':
			if (!HTMLArea.is_ie) {
				sel = this._getSelection();
				sel.removeAllRanges();
				range = this._createRange();
				range.selectNodeContents(this._doc.body);
				sel.addRange(range);
				HTMLArea._stopEvent(ev);
			}
			break;
		case 'b':
			cmd = "bold";
			break;
		case 'i':
			cmd = "italic";
			break;
		case 'u':
			cmd = "underline";
			break;
		case 's':
			cmd = "strikethrough";
			break;
		case 'l':
			cmd = "justifyleft";
			break;
		case 'e':
			cmd = "justifycenter";
			break;
		case 'r':
			cmd = "justifyright";
			break;
		case 'j':
			cmd = "justifyfull";
			break;
		case 'z':
			cmd = "undo";
			break;
		case 'y':
			cmd = "redo";
			break;
		case 'v':
			if (HTMLArea.is_ie || editor.config.htmlareaPaste) {
				cmd = "paste";
			}
			break;
		case 'n':
			cmd = "formatblock";
			value = "p";
			break;
		case '0':
			cmd = "killword";
			break;
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
			cmd = "formatblock";
			value = "h" + key;
			break;
		}
		if (cmd) {
			this.execCommand(cmd, false, value);
			HTMLArea._stopEvent(ev);
		}
	} else if (keyEvent) {
		switch (ev.keyCode) {
		case 13:
			if (HTMLArea.is_gecko && !ev.shiftKey)
				if (this.dom_checkInsertP(true))
					HTMLArea._stopEvent(ev);
			break;
		case 8:
		case 46:
			if (HTMLArea.is_gecko && !ev.shiftKey) {
				if (this.dom_checkBackspace())
					HTMLArea._stopEvent(ev);
			} else if (HTMLArea.is_ie) {
				if (this.ie_checkBackspace())
					HTMLArea._stopEvent(ev);
			}
			break;
		case 9:
			if (HTMLArea.is_ie) {
				if (editor.ie_tabPressed(ev.shiftKey))
					HTMLArea._stopEvent(ev);
			} else {
				if (editor.moz_tabPressed(ev.shiftKey))
					HTMLArea._stopEvent(ev);
			}
			break;
		}
	}
	if (editor._timerToolbar) {
		clearTimeout(editor._timerToolbar);
	}
	editor._timerToolbar = setTimeout(function() {
		editor.updateToolbar();
		editor._timerToolbar = null;
	}, 50);
};

HTMLArea.prototype.ie_tabPressed = function(shift) {
	var parents = this.getAncestorsHash();
	if (parents.table && parents.td) {
		var range = this._createRange(this._getSelection());
		range.moveToElementText(parents.td);
		if (shift)
			range.moveStart("character", -1);
		else
			range.moveEnd("character", 1);
		range.collapse(shift);
		range.select();
		parents = this.getAncestorsHash();
		if (parents.table && parents.td)
			this.selectNodeContents(parents.td);
	} else
		this.execCommand(shift ? "outdent" : "indent", false, null);
	return true;
};

HTMLArea.prototype.moz_tabPressed = function(shift) {
	var editor = this;
	setTimeout(function() {
		var parents = editor.getAncestorsHash();
		if (parents.table && parents.td)
			editor.selectNodeContents(parents.td);
	}, 20);
	return false;
};

HTMLArea.prototype.scrollToCaret = function() {
	var e = this.getParentElement(), w = this._iframe.contentWindow, h = w.innerHeight
			|| w.height, d = this._doc, t = d.documentElement.scrollTop
			|| d.body.scrollTop;
	if (typeof h == "undefined")
		return false;
	if (e.offsetTop > h + t)
		w.scrollTo(e.offsetLeft, e.offsetTop - h + e.offsetHeight);
};

HTMLArea.prototype.convertNode = function(el, newTagName) {
	var newel = newTagName ? this._doc.createElement(newTagName) : this._doc
			.createDocumentFragment(), p = el.parentNode;
	while (el.firstChild)
		newel.appendChild(el.firstChild);
	p.insertBefore(newel, el);
	p.removeChild(el);
	p.normalize();
	return newel;
};

HTMLArea.prototype.ie_checkBackspace = function() {
	var i, a, r2, sel = this._getSelection(), range = this._createRange(sel);
	if (/^(text|none)$/i.test(sel.type)) {
		r2 = range.duplicate();
		r2.moveStart("character", -1);
		a = r2.parentElement();
		if (a != range.parentElement() && /^a$/i.test(a.tagName)) {
			r2.collapse(true);
			r2.moveEnd("character", 1);
			r2.pasteHTML('');
			r2.select();
			return true;
		}
	} else
		for (var i = range.length; --i >= 0; a = range.item(i), a.parentNode
				.removeChild(a))
			;
	return false;
};

HTMLArea.prototype.dom_checkBackspace = function() {
	var self = this;
	if (HTMLArea._backspaceTimeout)
		clearTimeout(HTMLArea._backspaceTimeout);
	HTMLArea._backspaceTimeout = setTimeout(function() {
		try {
			var sel = self._getSelection();
			var r = self._createRange(sel);
			var SC = r.startContainer;
			var SO = r.startOffset;
			var EC = r.endContainer;
			var EO = r.endOffset;
			var newr = SC.nextSibling;
			if (SC.nodeType == 3)
				SC = SC.parentNode;
			if (SC.nodeType == 1 && !/\S/.test(SC.tagName)) {
				var p = document.createElement("p");
				while (SC.firstChild)
					p.appendChild(SC.firstChild);
				SC.parentNode.insertBefore(p, SC);
				SC.parentNode.removeChild(SC);
				r = r.cloneRange();
				r.setStartBefore(newr);
				r.setEndAfter(newr);
				r.extractContents();
				sel.removeAllRanges();
				sel.addRange(r);
			} else if (SC.nodeType == 1
					&& /^br$/i.test(SC.childNodes[SO - 1].tagName)) {
				newr = SC.childNodes[SO - 1];
				if (!newr.nextSibling) {
					r = r.cloneRange();
					r.setStartBefore(newr);
					r.collapse(true);
					sel.removeAllRanges();
					sel.addRange(r);
				}
			}
		} catch (e) {
		}
		HTMLArea._backspaceTimeout = 0;
	}, 10);
};

HTMLArea._formatTags = {
	b : "bold",
	strong : "bold",
	i : "italic",
	em : "italic",
	strike : "strikethrough",
	u : "underline"
};

HTMLArea._otherSaveCMDS = [ "justifyleft", "justifycenter", "justifyright",	"justifyfull", "fontname", "fontsize" ];

HTMLArea.prototype.dom_checkInsertP = function(step2) {
	var i, df, tmp, p = this.getAllAncestors(), block = null, pnodes = {}, stts = [], doc = this._doc, self = this;
	for (i = 0; i < p.length; ++i) {
		df = p[i];
		tmp = df.tagName.toLowerCase();
		if (!block && HTMLArea.isBlockElement(df) && !/^(body|html)$/.test(tmp))
			block = df;
		if (!pnodes[tmp])
			pnodes[tmp] = df;
	}
	if (step2) {
		for (i = p.length; --i >= 0;) {
			SC = HTMLArea._formatTags[p[i].tagName.toLowerCase()];
			if (SC)
				stts.push(SC);
		}
		for (i = HTMLArea._otherSaveCMDS.length; --i >= 0;) {
			try {
				df = HTMLArea._otherSaveCMDS[i];
				if (/justify/.test(df) && !block.align
						&& !block.style.textAlign || /font/.test(df)
						&& !pnodes.font)
					continue;
				tmp = /justify/.test(df) ? doc.queryCommandState(df) : doc
						.queryCommandValue(df);
				if (tmp)
					stts.push({
						cmd : df,
						value : tmp
					});
			} catch (e) {
			}
			;
		}
	}
	function make_p_later() {
		setTimeout(function() {
			self.execCommand("formatblock", false, "p");
			var i, tmp;
			for (i = stts.length; --i >= 0;) {
				tmp = stts[i];
				if (typeof tmp == "string")
					self.execCommand(tmp);
				else
					self.execCommand(tmp.cmd, false, tmp.value);
			}
			self.forceRedraw();
		}, 10);
	}
	;
	var btn = block && block.tagName.toLowerCase();
	if (!block || (btn == "blockquote")) {
		this.execCommand("formatblock", false, "p");
	} else if (/^(pre|tr|td|table|tbody|thead|th|caption|div|li)$/.test(btn)) {
		if (btn == "li" && !/\S/.test(HTMLArea.getInnerText(block)))
			make_p_later();
		return false;
	}
	if (step2) {
		if (block
				&& (/^h[1-6]$/.test(btn) || (pnodes.li && pnodes.p && /\S/
						.test(HTMLArea.getInnerText(pnodes.p))))) {
			var tmp, sel = this._getSelection(), r = this._createRange(sel), p = this._doc
					.createElement("p");
			if (block.lastChild.nodeType == 3)
				tmp = r.comparePoint(block.lastChild,
						block.lastChild.data.length) == 0;
			else
				tmp = r.compareNode(block.lastChild) == 1;
			if (tmp) {
				p.appendChild(this._doc.createElement("br"));
				sel.removeAllRanges();
				r.setEndAfter(block);
				r.collapse(false);
				r.insertNode(p);
				r.selectNodeContents(p);
				r.collapse(true);
				sel.addRange(r);
				this.scrollToCaret();
				return true;
			} else
				return false;
		} else {
			make_p_later();
			return false;
		}
	}
	return true;
};

HTMLArea.prototype.getHTML = function() {
	switch (this._editMode) {
	case "wysiwyg":
		this._wordClean();
		if (!this.config.fullPage) {
			return HTMLArea.getHTML(this._doc.body, false, this);
		} else
			return this.doctype + "\n"
					+ HTMLArea.getHTML(this._doc.documentElement, true, this);
	case "textmode":
		return this._textArea.value;
	default:
		alert("Mode <" + mode + "> not defined!");
	}
	return false;
};

HTMLArea.prototype.getInnerHTML = function() {
	switch (this._editMode) {
	case "wysiwyg":
		if (!this.config.fullPage)
			return this._doc.body.innerHTML;
		else
			return this.doctype + "\n" + this._doc.documentElement.innerHTML;
	case "textmode":
		return this._textArea.value;
	default:
		alert("Mode <" + mode + "> not defined!");
	}
	return false;
};

HTMLArea.prototype.setHTML = function(html) {
	switch (this._editMode) {
	case "wysiwyg":
		if (!this.config.fullPage)
			this._doc.body.innerHTML = html;
		else
			this._doc.body.innerHTML = html;
		this.convertSemanticTags(true);
		this._wordClean();
		break;
	case "textmode":
		this._textArea.value = html;
		break;
	default:
		alert("Mode <" + mode + "> not defined!");
	}
	return false;
};

HTMLArea.prototype.setDoctype = function(doctype) {
	this.doctype = doctype;
};

HTMLArea._object = null;
HTMLArea.cloneObject = function(obj) {
	if (!obj)
		return null;
	var newObj = new Object;
	if (obj.constructor.toString().indexOf("function Array(") == 1) {
		newObj = obj.constructor();
	}
	if (obj.constructor.toString().indexOf("function Function(") == 1) {
		newObj = obj;
	} else
		for ( var n in obj) {
			var node = obj[n];
			if (typeof node == 'object') {
				newObj[n] = HTMLArea.cloneObject(node);
			} else {
				newObj[n] = node;
			}
		}
	return newObj;
};

HTMLArea.checkSupportedBrowser = function() {
	if (HTMLArea.is_gecko) {
		if (navigator.productSub < 20021201) {
			alert("You need at least Mozilla-1.3 Alpha.\n"
					+ "Sorry, your Gecko is not supported.");
			return false;
		}
		if (navigator.productSub < 20030210) {
			/* alert("Mozilla < 1.3 Beta is not supported!\n"+"I'll try,
			 * though, but it might not work."); */
		}
	}
	return HTMLArea.is_midas || HTMLArea.is_ie;
};

HTMLArea.prototype._getSelection = function() {
	if (HTMLArea.is_ie) {
		return this._doc.selection;
	} else {
		return this._iframe.contentWindow.getSelection();
	}
};

HTMLArea.normalizeRange = function(r) {
	try {
		var tmp = null, SC = r.startContainer, SO = r.startOffset, EC = r.endContainer, EO = r.endOffset;
		if (SC.nodeType == 3 && EC.nodeType == 3)
			return;
		if (SC.nodeType == 1) {
			while (!tmp && SO >= 0) {
				tmp = SC.childNodes[SO--];
			}
			while (tmp && tmp.nodeType != 3)
				tmp = tmp.firstChild;
			if (tmp)
				r.setStart(tmp, tmp.data.length);
		}
		tmp = null;
		if (EC.nodeType == 1) {
			while (!tmp && EO >= 0) {
				tmp = EC.childNodes[EO--];
			}
			while (tmp && tmp.nodeType != 3)
				tmp = tmp.lastChild;
			if (tmp)
				r.setEnd(tmp, tmp.data.length);
		}
	} catch (e) {
	}
	;
};

HTMLArea.prototype._createRange = function(sel) {
	if (HTMLArea.is_ie) {
		if (sel)
			return sel.createRange();
		else
			return this._doc.body.createTextRange();
	} else {
		if (typeof sel != "undefined") {
			try {
				return sel.getRangeAt(0);
			} catch (e) {
				return this._doc.createRange();
			}
		} else {
			return this._doc.createRange();
		}
	}
};

HTMLArea._addEvent = function(el, evname, func) {
	if (HTMLArea.is_ie) {
		el.attachEvent("on" + evname, func);
	} else {
		el.addEventListener(evname, func, true);
	}
};

HTMLArea._addEvents = function(el, evs, func) {
	for (var i = evs.length; --i >= 0;) {
		HTMLArea._addEvent(el, evs[i], func);
	}
};

HTMLArea._removeEvent = function(el, evname, func) {
	if (HTMLArea.is_ie) {
		el.detachEvent("on" + evname, func);
	} else {
		el.removeEventListener(evname, func, true);
	}
};

HTMLArea._removeEvents = function(el, evs, func) {
	for (var i = evs.length; --i >= 0;) {
		HTMLArea._removeEvent(el, evs[i], func);
	}
};

HTMLArea._stopEvent = function(ev) {
	if (HTMLArea.is_ie) {
		ev.cancelBubble = true;
		ev.returnValue = false;
	} else {
		ev.preventDefault();
		ev.stopPropagation();
	}
};

HTMLArea._removeClass = function(el, className) {
	if (!(el && el.className)) {
		return;
	}
	var cls = el.className.split(" ");
	var ar = new Array();
	for (var i = cls.length; i > 0;) {
		if (cls[--i] != className) {
			ar[ar.length] = cls[i];
		}
	}
	el.className = ar.join(" ");
};

HTMLArea._addClass = function(el, className) {
	HTMLArea._removeClass(el, className);
	el.className += " " + className;
};

HTMLArea._hasClass = function(el, className) {
	if (!(el && el.className)) {
		return false;
	}
	var cls = el.className.split(" ");
	for (var i = cls.length; i > 0;) {
		if (cls[--i] == className) {
			return true;
		}
	}
	return false;
};

HTMLArea._blockTags = " body form textarea fieldset ul ol dl li div blockquote "
		+ "p h1 h2 h3 h4 h5 h6 quote pre table thead "
		+ "tbody tfoot tr td iframe address ";
HTMLArea.isBlockElement = function(el) {
	return el
			&& el.nodeType == 1
			&& (HTMLArea._blockTags.indexOf(" " + el.tagName.toLowerCase()
					+ " ") != -1);
};
HTMLArea._quickTags = " br hr input link meta img ";
HTMLArea.needsClosingTag = function(el) {
	return el
			&& el.nodeType == 1
			&& HTMLArea._quickTags
					.indexOf(" " + el.tagName.toLowerCase() + " ") == -1;
};
HTMLArea.htmlEncode = function(str) {
	return str.replace(/&/ig, "&amp;").replace(/</ig, "&lt;").replace(/>/ig,
			"&gt;").replace(/\x22/ig, "&quot;").replace(/\u00A0/g, "&#xa0;");
};
HTMLArea.getHTML = function(root, outputRoot, editor) {
	try {
		return HTMLArea.getHTMLWrapper(root, outputRoot, editor);
	} catch (e) {
		alert(e);
		return editor._iframe.contentWindow.document.body.innerHTML;
	}
};
HTMLArea.getHTML_fixCSS = function(css) {
	return css;
};
HTMLArea.getHTMLWrapper = function(root, outputRoot, editor) {
	var html = [];
	switch (root.nodeType) {
	case 1:
	case 11:
		var closed;
		var i;
		var root_tag = (root.nodeType == 1) ? root.tagName.toLowerCase() : '';
		if (root_tag == 'br' && !root.nextSibling && root.previousSibling)
			break;
		if (outputRoot)
			outputRoot = !(editor.config.htmlRemoveTags && editor.config.htmlRemoveTags
					.test(root_tag));
		if (HTMLArea.is_ie && root_tag == "head") {
			if (outputRoot)
				html.push("<head>");
			var save_multiline = RegExp.multiline;
			RegExp.multiline = true;
			var txt = root.innerHTML.replace(HTMLArea.RE_tagName, function(str,
					p1, p2) {
				return p1 + p2.toLowerCase();
			});
			RegExp.multiline = save_multiline;
			html.push(txt);
			if (outputRoot)
				html.push("</head>");
			break;
		} else if (outputRoot) {
			root_tag = HTMLArea.convertTag(root_tag);
			closed = (!(root.hasChildNodes() || HTMLArea.needsClosingTag(root)));
			html.push("<" + root_tag);
			var attrs = root.attributes;
			for (i = 0; i < attrs.length; ++i) {
				var a = attrs.item(i);
				if (!a.specified) {
					continue;
				}
				var name = a.nodeName.toLowerCase();
				if (/_moz_editor_bogus_node/.test(name)) {
					html = [];
					break;
				}
				if (/_moz|contenteditable|_msh/.test(name)) {
					continue;
				}
				var value;
				if (name != "style") {
					if (typeof root[a.nodeName] != "undefined"
							&& name != "href" && name != "src"
							&& !/^on/.test(name)) {
						value = root[a.nodeName];
					} else {
						value = a.nodeValue;
						if (HTMLArea.is_ie && (name == "href" || name == "src")) {
							value = editor.stripBaseURL(value);
						}
					}
				} else {
					value = HTMLArea.getHTML_fixCSS(root.style.cssText);
				}
				if (/(_moz|^$)/.test(value)) {
					continue;
				}
				html.push(" " + name + '="' + value + '"');
			}
			if (html.length > 0) {
				html.push(closed ? " />" : ">");
			}
		}
		for (i = root.firstChild; i; i = i.nextSibling) {
			html.push(HTMLArea.getHTMLWrapper(i, true, editor));
		}
		if (outputRoot && !closed) {
			html.push("</" + root_tag + ">");
		}
		break;
	case 3:
		if (/^(script|style)$/i.test(root.parentNode.tagName)) {
			if (root.data.indexOf("/*<![CDATA[*/") != 0)
				html = [ "/*<![CDATA[*/" + root.data + "/*]]>*/" ];
			else
				html = [ root.data ];
		} else
			html = [ HTMLArea.htmlEncode(root.data) ];
		break;
	case 4:
		alert("CDATA section encountered");
		html = [ "<![CDATA[" + root.data.replace(/--/g, '—') + "]]>" ];
		break;
	case 8:
		html = [ "<!--" + root.data + "-->" ];
		break;
	}
	return html.join("");
};

HTMLArea.prototype.convertSemanticTags = function(use_b, root) {
	var src1 = new RegExp("^" + (use_b ? "strong" : "b") + "$", "i"), dst1 = use_b ? "b"
			: "strong", src2 = new RegExp("^" + (use_b ? "em" : "i") + "$", "i"), dst2 = use_b ? "i"
			: "em", ed = this;
	function rec(root) {
		if (root) {
			if (src1.test(root.tagName))
				root = ed.convertNode(root, dst1);
			else if (src2.test(root.tagName))
				root = ed.convertNode(root, dst2);
			for (var i = root.firstChild; i; i = i.nextSibling)
				if (i.nodeType == 1)
					i = rec(i);
		} else
			root = null;
		return root;
	}
	;
	rec(root || this._doc.body);
};

HTMLArea.convertTag = function(tag) {
	switch (tag) {
	case "b":
		return "strong";
	case "i":
		return "em";
	}
	return tag;
};

HTMLArea.getPrevNode = function(node) {
	if (!node)
		return null;
	if (node.previousSibling)
		return node.previousSibling;
	if (node.parentNode)
		return node.parentNode;
	return null;
};

HTMLArea.getNextNode = function(node) {
	if (!node)
		return null;
	if (node.nextSibling)
		return node.nextSibling;
	if (node.parentNode)
		return node.parentNode;
	return null;
};

HTMLArea.prototype.stripBaseURL = function(string) {
	var baseurl = this.config.baseURL;
	baseurl = baseurl.replace(/[^\/]+$/, '');
	var basere = new RegExp(baseurl);
	string = string.replace(basere, "");
	baseurl = baseurl.replace(/^(https?:\/\/[^\/]+)(.*)$/, '$1');
	basere = new RegExp(baseurl);
	return string.replace(basere, "");
};
String.prototype.trim = function() {
	return this.replace(/^\s+/, '').replace(/\s+$/, '');
};
HTMLArea._makeColor = function(v) {
	if (typeof v != "number") {
		return v;
	}
	var r = v & 0xFF;
	var g = (v >> 8) & 0xFF;
	var b = (v >> 16) & 0xFF;
	return "rgb(" + r + "," + g + "," + b + ")";
};
HTMLArea.do_false = function() {
	return false;
};
HTMLArea._colorToRgb = function(v) {
	if (!v)
		return '';
	function hex(d) {
		return (d < 16) ? ("0" + d.toString(16)) : d.toString(16);
	}
	;
	if (typeof v == "number") {
		var r = v & 0xFF;
		var g = (v >> 8) & 0xFF;
		var b = (v >> 16) & 0xFF;
		return "#" + hex(r) + hex(g) + hex(b);
	}
	if (v.substr(0, 3) == "rgb") {
		var re = /rgb\s*\(\s*([0-9]+)\s*,\s*([0-9]+)\s*,\s*([0-9]+)\s*\)/;
		if (v.match(re)) {
			var r = parseInt(RegExp.$1);
			var g = parseInt(RegExp.$2);
			var b = parseInt(RegExp.$3);
			return "#" + hex(r) + hex(g) + hex(b);
		}
		return null;
	}
	if (v.substr(0, 1) == "#") {
		return v;
	}
	if (HTMLArea._colors && typeof v == "string") {
		return HTMLArea._colors[v.toLowerCase()];
	}
	return null;
};
HTMLArea.prototype._popupDialog = function(url, action, init) {
	Dialog(this.popupURL(url), action, init);
};
HTMLArea.prototype.imgURL = function(file, plugin) {
	if (typeof plugin == "undefined")
		return _editor_url + file;
	else
		return _editor_url + "plugins/" + plugin + "/img/" + file;
};
HTMLArea.prototype.popupURL = function(file) {
	var url = "";
	if (file.match(/^plugin:\/\/(.*?)\/(.*)/)) {
		var plugin = RegExp.$1;
		var popup = RegExp.$2;
		if (!/\.html$/.test(popup))
			popup += ".html";
		url = _editor_url + "plugins/" + plugin + "/popups/" + popup;
	} else
		url = _editor_url + this.config.popupURL + file;
	return url;
};
HTMLArea.getElementById = function(tag, id) {
	var el, i, objs = document.getElementsByTagName(tag);
	for (i = objs.length; --i >= 0 && (el = objs[i]);)
		if (el.id == id)
			return el;
	return null;
};
HTMLArea._openedWindows = [];
HTMLArea.tryWindowOpen = function(arg1, arg2, arg3) {
	try {
		var win = window.open(arg1, arg2, arg3);
		if (win) {
			setTimeout(function() {
				HTMLArea._addEvent(win, "unload", function() {
					var a = HTMLArea._openedWindows, i;
					for (i = a.length; --i >= 0;) {
						if (a[i] === win)
							a.splice(i, 1);
					}
				});
			}, 100);
			HTMLArea._openedWindows.push(win);
			return win;
		}
		throw "blocked";
	} catch (e) {
		alert("Your browser blocked our popup!\n\n"
				+ "We need you to allow popups dialog for this page\n"
				+ "in order for the editor to work.  We will not open\n"
				+ "unrequested popups.\n\n"
				+ "To allow popups from this page please click\n"
				+ "the bar that just popped under the toolbar.");
		return null;
	}
};
HTMLArea._addEvent(window, "unload", function() {
	var a = HTMLArea._openedWindows, i;
	for (i = a.length; --i >= 0;) {
		a[i].close();
		a.splice(i, 1);
	}
});
HTMLArea.centeredWindowOpen = function(url, size, name) {
	if (!name)
		name = "_blank";
	if (!size)
		size = {
			w : 500,
			h : 350
		};
	var args;
	if (HTMLArea.is_ie)
		args = "toolbar=no,location=no,directories=no,status=no,menubar=no,";
	else
		args = "toolbar=no,menubar=no,personalbar=no,status=no,";
	args += "scrollbars=no,resizable=yes,width=" + size.w + ",height=" + size.h;
	var left = 0, top = 0;
	if (screen.width)
		left = (screen.width - size.w) / 2;
	if (screen.height)
		top = (screen.height - size.h) / 2;
	if (left)
		args += ",left=" + left;
	if (top)
		args += ",top=" + top;
	return HTMLArea.tryWindowOpen(url, name, args);
};
HTMLArea.onBeforeUnload = function() {
	return "Leaving this page will dismiss any changes you may have made to this document.  "
			+ "Please confirm that you want to disregard the changes.";
};
//window.onbeforeunload = HTMLArea.onBeforeUnload;
