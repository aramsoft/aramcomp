// ListType Plugin for HTMLArea-4.0
// Previously sponsored by MEdTech Unit - Queen's University
// Current implementation by Inferior Products Corporation, http://Inferior-Products.com
// Previous implementation by Mihai Bazon, http://dynarch.com/mishoo/
//
// (c) 2003-2013 Inferior-Products.com
// (c) dynarch.com 2003.
// Distributed under the same terms as <HTMLArea /> itself.
// This notice MUST stay intact for use (see license.txt).
//
// $Id: list-type.js,v 1.1.1.3 2013-07-29 19:29:43 sfchris Exp $
function ListType(editor){this.editor=editor;var cfg=editor.config;var toolbar=cfg.toolbar;var self=this;var i18n=ListType.I18N;var options={};options[i18n["Decimal"]]="decimal";options[i18n["Lower roman"]]="lower-roman";options[i18n["Upper roman"]]="upper-roman";options[i18n["Lower latin"]]="lower-alpha";options[i18n["Upper latin"]]="upper-alpha";if(!HTMLArea.is_ie)options[i18n["Lower greek"]]="lower-greek";var obj={id:"ListType",tooltip:i18n["ListStyleTooltip"],options:options,action:function(editor){self.onSelect(editor,this);},refresh:function(editor){self.updateValue(editor,this);},context:"ol"};cfg.registerDropdown(obj);var a,i,j,found=false;for(i=0;!found&&i<toolbar.length;++i){a=toolbar[i];for(j=0;j<a.length;++j){if(a[j]=="unorderedlist"){found=true;break;}}}if(found)a.splice(j,0,"space","ListType","space");};ListType._pluginInfo={name:"ListType",version:"1.1",developer:"SF_chris",developer_url:"http://Inferior-Products.com/",c_owner:"Inferior-Products",sponsor:"MEdTech Unit - Queen's University",sponsor_url:"http://www.queensu.ca/",license:"htmlArea"};ListType.prototype.onSelect=function(editor,combo){var tbobj=editor._toolbarObjects[combo.id].element;var parent=editor.getParentElement();while(!/^ol$/i.test(parent.tagName)){parent=parent.parentNode;}parent.style.listStyleType=tbobj.value;};ListType.prototype.updateValue=function(editor,combo){var tbobj=editor._toolbarObjects[combo.id].element;var parent=editor.getParentElement();while(parent&&!/^ol$/i.test(parent.tagName)){parent=parent.parentNode;}if(!parent){tbobj.selectedIndex=0;return;}var type=parent.style.listStyleType;if(!type){tbobj.selectedIndex=0;}else{for(var i=tbobj.firstChild;i;i=i.nextSibling){i.selected=(type.indexOf(i.value)!=-1);}}};
