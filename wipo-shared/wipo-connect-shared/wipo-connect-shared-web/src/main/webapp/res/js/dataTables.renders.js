function colRender(renderName, options){

	options = options || {};



	var renderMap = {
			"default": function(data, type, row, meta) {
				if(data == null) return "";
				return data;
			},
			"string": function(data, type, row, meta) {
				if(data == null) return "";
				if (type !== "display") return data;
				return escapeHtml(data);
			},
			"date" : function(data, type, row, meta) {
				if(!data) return "";
				
				var momentData = $.isNumeric(data) ? moment(data) : moment(data, moment.format.date);
				if (!momentData.isValid()) {
					momentData = moment(data, moment.format.iso8601);
				}

				if (type === "type" || type === "sort") {
					return momentData.format("YYYYMMDD"); // SORTABLE DATE FORMAT
				} else {
					return momentData.format(moment.format.date); // LOCALE DATE FORMAT
				}
			},
			"dateTime" : function(data, type, row, meta) {
				if(!data) return "";
				
				var momentData = $.isNumeric(data) ? moment(data) : moment(data, moment.format.dateTime);
				if (!momentData.isValid()) {
					momentData = moment(data, moment.format.iso8601);
				}

				if (type === "type" || type === "sort") {
					return momentData.format("YYYYMMDD HHmmss"); // SORTABLE DATETIME FORMAT
				} else {
					return momentData.format(moment.format.dateTime);
				}
			},
			"yearFromDate" : function(data, type, row, meta) {
				if(!data) return "";
				
				var momentData = $.isNumeric(data) ? moment(data) : moment(data, moment.format.date);
				if (!momentData.isValid()) {
					momentData = moment(data, moment.format.iso8601);
				}
				
				return momentData.format("YYYY");
			},
			"amount" : function(data, type, row, meta) {
				if(data == null) return "";

				if (type === "type" || type === "sort") {
					return data; // SORTABLE
				} else {
					if(options.scale == null){
						return NumericConversionUtils.formatAmount(data);
					}else{
						return NumericConversionUtils.formatAmountWithScale(data,options.scale);
					}
				}
			},
			"decimal" : function(data, type, row, meta) {
				if(data == null) return "";

				if (type === "type" || type === "sort") {
					return data; // SORTABLE
				} else {
					if(options.scale == null){
						return NumericConversionUtils.format(data);
					}else{
						return NumericConversionUtils.formatWithScale(data,options.scale);
					}
				}
			},
			"integer" : function(data, type, row, meta) {
				if(!data) return "";

				if (type === "type" || type === "sort") {
					return data; // SORTABLE
				} else {
					return NumericConversionUtils.formatInteger(data);
				}
			},
			"integer_with_default_value" : function(data, type, row, meta) {
				if(!data) return "0";

				if (type === "type" || type === "sort") {
					return data; // SORTABLE
				} else {
					return NumericConversionUtils.formatInteger(data);
				}
			},
			"button" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					return "<button type='button' class='btn btn-xs btn-primary " + options.cssClass + "'>"+ options.text +"</button>";
				}
			},
			"checkboxId" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					return "<input type='checkbox' class='chk-id select-row' value='"+ data +"' />";
				}
			},
			"buttonIco" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					return "<button type='button' class='btn btn-xs btn-primary " + options.cssClass + "'><span class='glyphicon "+ options.icon +"'></span></button>";
				}
			},
			"array" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					return data.sort().join(", ");
				}
			},
			"boolean" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					if (!data) return "";
					
					if (data == "true") {
						return "Yes";
					}
					return "No";
				}
			},
			"multiple" : function(data, type, row, meta) {
				if (type !== 'display') {
					return data;
				} else {
					if (!data) return "";
					return data.replace(/,/g, ", ");
				}
			},
			"caseConversion" : function(data, type, row, meta) {
				if(type !== "display") return data;
				
				var caseData = $("<div>").html(data).text();
				
				switch(globalParams.caseConversionSelected) {
			    case globalParams.fieldCaseConvLowercase:
			    	caseData = caseData.toLowerCase();
			        break;
			 	case globalParams.fieldCaseConvUppercase:
			 		caseData = caseData.toUpperCase();
		        break;
				case globalParams.fieldCaseConvFirstCapital:
					caseData = caseData.toLowerCase().replace(/^\w/, function (chr) {
			    		return chr.toUpperCase();
			    	});
			        break;
				case globalParams.fieldCaseConvAllCapital:
					caseData = caseData.replace(/\S+/g, function(txt){
				        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
				    });
			        break;
			    default:
			    	
				}
				return $("<div>").text(caseData).html();
			}
		};

	var out = renderMap[renderName];

	if(!out) console.warn("colRender '" + renderName + "' not found!");

	return out;
}