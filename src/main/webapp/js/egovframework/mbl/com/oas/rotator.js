(function($){ $.fn.rotator = function(options){

    var defaults = {
		ms: 5000,
		n: 1,
		autoHeight: false
	};
  
    var options = $.extend(defaults, options);
	
	return this.each(function(index) {
		
		var $this = $(this);
		
		var initialHeight = 0;
		$this.children().filter(":lt("+options.n+")").each(function(index,item){
		    initialHeight += $(item).height();
		});
		
		$this.height(initialHeight);
		
		function beginRotation(){
            $this.data("timer", setInterval(function(){
                
                var childHeight = $this.children().filter(":first-child").height();
                var animParams = {scrollTop: (childHeight) + "px"};
                var autoHeight = 0;
                $this.children().filter(":lt("+(options.n+1)+")").each(function(index,item){
                    if(index>0)autoHeight += $(item).height();
                });
                if(options.autoHeight)animParams = $.extend({height:(autoHeight) + "px"}, animParams);
            
                
                $this.animate(animParams, 500, function(){
                    $this.scrollTop(0);
                    $this.append($this.children().filter(":first-child"));
                    $this.css("overflow","hidden");
                });

                
            }, options.ms));
		}
		
		$this.mouseover(function(){
		    clearTimeout($this.data("timer"));
		});
		
		$this.mouseout(function(){
		    beginRotation();
		});
		
		beginRotation();

	});

  
}})(jQuery);
