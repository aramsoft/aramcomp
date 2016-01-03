// htmlArea v4.0 - Copyright (c) 2003-2013 http://Inferior-Products.com
// htmlArea v3.0 - Copyright (c) 2002, 2003 interactivetools.com, inc.
// This copyright notice MUST stay intact for use (see license.txt).
//
// Portions (c) dynarch.com, 2003
//
// A free WYSIWYG editor replacement for <textarea> fields.
// For full source code and docs, visit http://xhtmlarea.ORG/
//
// Version 4.0 developed by SF_chris.
// http://Inferior-Products.com
//
// Version 3.0 developed by Mihai Bazon.
//   http://dynarch.com/mishoo
//
// $Id: popup.js,v 1.6 2013-07-29 20:38:41 sfchris Exp $
function getAbsolutePos(el) {
	var r = {
		x : el.offsetLeft,
		y : el.offsetTop
	};
	if (el.offsetParent) {
		var tmp = getAbsolutePos(el.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y;
	}
	return r;
};
function comboSelectValue(c, val) {
	var ops = c.getElementsByTagName("option");
	for (var i = ops.length; --i >= 0;) {
		var op = ops[i];
		op.selected = (op.value == val);
	}
	c.value = val;
};
function __dlg_onclose() {
	opener.Dialog._return(null);
};
function __dlg_init(bottom) {
	var body = document.body;
	var body_height = 0;
	if (typeof bottom == "undefined") {
		var div = document.createElement("div");
		body.appendChild(div);
		var pos = getAbsolutePos(div);
		body_height = pos.y;
	} else {
		var pos = getAbsolutePos(bottom);
		body_height = pos.y + bottom.offsetHeight;
	}
	window.dialogArguments = opener.Dialog._arguments;
	if (!document.all) {
		window.sizeToContent();
		window.sizeToContent();
		window.addEventListener("unload", __dlg_onclose, true);
		window.innerWidth = body.offsetWidth + 5;
		window.innerHeight = body_height + 2;
		var x = opener.screenX + (opener.outerWidth - window.outerWidth) / 2;
		var y = opener.screenY + (opener.outerHeight - window.outerHeight) / 2;
		window.moveTo(x, y);
	} else {
		window.resizeTo(body.offsetWidth, body_height);
		var ch = body.clientHeight;
		var cw = body.clientWidth;
		window.resizeBy(body.offsetWidth - cw, body_height - ch);
		var W = body.offsetWidth;
		var H = 2 * body_height - ch;
		var x = (screen.availWidth - W) / 2;
		var y = (screen.availHeight - H) / 2;
		window.moveTo(x, y);
	}
	document.body.onkeypress = __dlg_close_on_esc;
};
function __dlg_translate(i18n) {
	var types = [ "input", "select", "legend", "span", "option", "td",
			"button", "div" ];
	for (var type = 0; type < types.length; ++type) {
		var spans = document.getElementsByTagName(types[type]);
		for (var i = spans.length; --i >= 0;) {
			var span = spans[i];
			if (span.firstChild && span.firstChild.data) {
				var txt = i18n[span.firstChild.data];
				if (txt)
					span.firstChild.data = txt;
			}
			if (span.title) {
				var txt = i18n[span.title];
				if (txt)
					span.title = txt;
			}
		}
	}
	var txt = i18n[document.title];
	if (txt)
		document.title = txt;
};
function __dlg_close(val) {
	opener.Dialog._return(val);
	window.close();
};
function __dlg_close_on_esc(ev) {
	ev || (ev = window.event);
	if (ev.keyCode == 27) {
		window.close();
		return false;
	}
	return true;
};
