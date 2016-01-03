/*
	Copyright (c) 2011, Andy andyMatthews, http://andyMatthews.net
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
*/

$(function(){

	$('div').live('pageshow',function(event, ui){

		// console.log(getUrlVars());
		
		// remove any existing swipe areas
		$('.aDeleteBtn').remove();
		// remove any existing swipe areas
		$('.divSwipe').remove();
		
		$('ul li').unbind('swiperight').bind('swiperight', function(e){
			// reference the just swiped list item
			var $li = $(this);
			
			
			// remove all swipe divs first
			$('.aDeleteBtn').remove();
			
			$('.divSwipe').remove();
			$('.divSwipe2').remove();
			
			if($li.data('swipefg') == 'delete') {
				// remove all buttons first
				
				// create buttons and div container
				var $aDeleteBtn = $('<a>Delete</a>')
								.attr({
									'class': 'aDeleteBtn ui-btn-up-r',
									'href': 'page.html'
								}).bind({
									click: function(e){
										e.preventDefault();
										$(this).parents('li').slideUp();
									}
								});
				// insert swipe div into list item
				$li.prepend($aDeleteBtn);
			}			
			else if($li.data('swipefg') == 'menu') {					
				
				// create buttons and div container
				var $divSwipe = $('<div class="divSwipe2"></div>');
				var $myBtn01 = $('<a>Button1</a>')
								.attr({
									'class': 'aSwipeBtn ui-btn-up-b',
									'href': $li.data('swipeurl')
								});
				var $myBtn02 = $('<a>Button2</a>')
								.attr({
									'class': 'aSwipeBtn ui-btn-up-e',
									'href': $li.data('swipeurl2')
								});
			
				// insert swipe div into list item
				//$li.prepend($divSwipe);
				$li.append($divSwipe);
				// insert buttons into divSwipe
				$divSwipe.prepend($myBtn01,$myBtn02).show(100);
			
			}
			
			// add escape route for swipe menu
			$('body').bind('tap', function(e){
				// if the triggering object is a button, fire it's tap event
				if (e.target.className.indexOf('aSwipeBtn') >= 0) $(e.target).trigger('click'); 
				if (e.target.className.indexOf('aDeleteBtn') >= 0) $(e.target).trigger('click');
				// remove any existing cancel buttons
				
				$('.aDeleteBtn').remove();
				
				$('.divSwipe').remove();
				$('.divSwipe2').remove();
				
				// remove the event
				$('body').unbind('tap');
			});
			
		});

		function getUrlVars() {
			var vars = [], hash;
			var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			for(var i = 0; i < hashes.length; i++) {
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		}

	});

});