/**
 * Formula Utils
 **/

var FormulaUtils =
  {
	 validateFormula: function(formula,varList){
		 var frm = "(function (){\n";
		 var res;
		 var isValid;
		 
		 frm += "\"use strict\";\n";

		 $(varList).each(function(){
			 frm += "var " + this + "=1;\n"
		 });

		 frm +=  "\nreturn " + formula + ";";
		 frm += "\n})();";

		 try{
			 res = eval(frm);
			 isValid = true;
		 }catch(e){
			 isValid = false;
		 }

		 return isValid;
	 },

	 executeFormula: function(formula,params){
		 var frm = "(function (){\n";
		 var res;
		 
		 frm += "\"use strict\";\n";

		 for(var k in params){
			 frm += "var " + k + "=" + params[k] + ";\n"
		 }
		 
		 frm +=  "\nreturn " + formula + ";";
		 frm += "\n})();";

		 console.debug(frm);

		 res = eval(frm);

		 return res;
	 }
 };
