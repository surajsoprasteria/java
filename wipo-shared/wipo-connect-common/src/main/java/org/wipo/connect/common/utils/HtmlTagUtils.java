/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.utils;

import java.util.Arrays;
import java.util.List;

/**
 * The Class HtmlTagUtils.
 */
public class HtmlTagUtils {

	private HtmlTagUtils() {
		super();
	}

	/**
	 * The Constant COMMENT.
	 */
	public static final String COMMENT = "!--";
	
	/**
	 * The Constant DOCTYPE.
	 */
	public static final String DOCTYPE = "!DOCTYPE";
	
	/**
	 * The Constant A.
	 */
	public static final String A = "a";
	
	/**
	 * The Constant ABBR.
	 */
	public static final String ABBR = "abbr";
	
	/**
	 * The Constant ACRONYM.
	 */
	public static final String ACRONYM = "acronym";
	
	/**
	 * The Constant ADDRESS.
	 */
	public static final String ADDRESS = "address";
	
	/**
	 * The Constant APPLET.
	 */
	public static final String APPLET = "applet";
	
	/**
	 * The Constant AREA.
	 */
	public static final String AREA = "area";
	
	/**
	 * The Constant ARTICLE.
	 */
	public static final String ARTICLE = "article";
	
	/**
	 * The Constant ASIDE.
	 */
	public static final String ASIDE = "aside";
	
	/**
	 * The Constant AUDIO.
	 */
	public static final String AUDIO = "audio";
	
	/**
	 * The Constant B.
	 */
	public static final String B = "b";
	
	/**
	 * The Constant BASE.
	 */
	public static final String BASE = "base";
	
	/**
	 * The Constant BASEFONT.
	 */
	public static final String BASEFONT = "basefont";
	
	/**
	 * The Constant BDI.
	 */
	public static final String BDI = "bdi";
	
	/**
	 * The Constant BDO.
	 */
	public static final String BDO = "bdo";
	
	/**
	 * The Constant BIG.
	 */
	public static final String BIG = "big";
	
	/**
	 * The Constant BLOCKQUOTE.
	 */
	public static final String BLOCKQUOTE = "blockquote";
	
	/**
	 * The Constant BODY.
	 */
	public static final String BODY = "body";
	
	/**
	 * The Constant BR.
	 */
	public static final String BR = "br";
	
	/**
	 * The Constant BUTTON.
	 */
	public static final String BUTTON = "button";
	
	/**
	 * The Constant CANVAS.
	 */
	public static final String CANVAS = "canvas";
	
	/**
	 * The Constant CAPTION.
	 */
	public static final String CAPTION = "caption";
	
	/**
	 * The Constant CENTER.
	 */
	public static final String CENTER = "center";
	
	/**
	 * The Constant CITE.
	 */
	public static final String CITE = "cite";
	
	/**
	 * The Constant CODE.
	 */
	public static final String CODE = "code";
	
	/**
	 * The Constant COL.
	 */
	public static final String COL = "col";
	
	/**
	 * The Constant COLGROUP.
	 */
	public static final String COLGROUP = "colgroup";
	
	/**
	 * The Constant DATALIST.
	 */
	public static final String DATALIST = "datalist";
	
	/**
	 * The Constant DD.
	 */
	public static final String DD = "dd";
	
	/**
	 * The Constant DEL.
	 */
	public static final String DEL = "del";
	
	/**
	 * The Constant DETAILS.
	 */
	public static final String DETAILS = "details";
	
	/**
	 * The Constant DFN.
	 */
	public static final String DFN = "dfn";
	
	/**
	 * The Constant DIALOG.
	 */
	public static final String DIALOG = "dialog";
	
	/**
	 * The Constant DIR.
	 */
	public static final String DIR = "dir";
	
	/**
	 * The Constant DIV.
	 */
	public static final String DIV = "div";
	
	/**
	 * The Constant DL.
	 */
	public static final String DL = "dl";
	
	/**
	 * The Constant DT.
	 */
	public static final String DT = "dt";
	
	/**
	 * The Constant EM.
	 */
	public static final String EM = "em";
	
	/**
	 * The Constant EMBED.
	 */
	public static final String EMBED = "embed";
	
	/**
	 * The Constant FIELDSET.
	 */
	public static final String FIELDSET = "fieldset";
	
	/**
	 * The Constant FIGCAPTION.
	 */
	public static final String FIGCAPTION = "figcaption";
	
	/**
	 * The Constant FIGURE.
	 */
	public static final String FIGURE = "figure";
	
	/**
	 * The Constant FONT.
	 */
	public static final String FONT = "font";
	
	/**
	 * The Constant FOOTER.
	 */
	public static final String FOOTER = "footer";
	
	/**
	 * The Constant FORM.
	 */
	public static final String FORM = "form";
	
	/**
	 * The Constant FRAME.
	 */
	public static final String FRAME = "frame";
	
	/**
	 * The Constant FRAMESET.
	 */
	public static final String FRAMESET = "frameset";
	
	/**
	 * The Constant H1.
	 */
	public static final String H1 = "h1";
	
	/**
	 * The Constant H2.
	 */
	public static final String H2 = "h2";
	
	/**
	 * The Constant H3.
	 */
	public static final String H3 = "h3";
	
	/**
	 * The Constant H4.
	 */
	public static final String H4 = "h4";
	
	/**
	 * The Constant H5.
	 */
	public static final String H5 = "h5";
	
	/**
	 * The Constant H6.
	 */
	public static final String H6 = "h6";
	
	/**
	 * The Constant HEAD.
	 */
	public static final String HEAD = "head";
	
	/**
	 * The Constant HEADER.
	 */
	public static final String HEADER = "header";
	
	/**
	 * The Constant HR.
	 */
	public static final String HR = "hr";
	
	/**
	 * The Constant HTML.
	 */
	public static final String HTML = "html";
	
	/**
	 * The Constant I.
	 */
	public static final String I = "i";
	
	/**
	 * The Constant IFRAME.
	 */
	public static final String IFRAME = "iframe";
	
	/**
	 * The Constant IMG.
	 */
	public static final String IMG = "img";
	
	/**
	 * The Constant INPUT.
	 */
	public static final String INPUT = "input";
	
	/**
	 * The Constant INS.
	 */
	public static final String INS = "ins";
	
	/**
	 * The Constant KBD.
	 */
	public static final String KBD = "kbd";
	
	/**
	 * The Constant KEYGEN.
	 */
	public static final String KEYGEN = "keygen";
	
	/**
	 * The Constant LABEL.
	 */
	public static final String LABEL = "label";
	
	/**
	 * The Constant LEGEND.
	 */
	public static final String LEGEND = "legend";
	
	/**
	 * The Constant LI.
	 */
	public static final String LI = "li";
	
	/**
	 * The Constant LINK.
	 */
	public static final String LINK = "link";
	
	/**
	 * The Constant MAIN.
	 */
	public static final String MAIN = "main";
	
	/**
	 * The Constant MAP.
	 */
	public static final String MAP = "map";
	
	/**
	 * The Constant MARK.
	 */
	public static final String MARK = "mark";
	
	/**
	 * The Constant MENU.
	 */
	public static final String MENU = "menu";
	
	/**
	 * The Constant MENUITEM.
	 */
	public static final String MENUITEM = "menuitem";
	
	/**
	 * The Constant META.
	 */
	public static final String META = "meta";
	
	/**
	 * The Constant METER.
	 */
	public static final String METER = "meter";
	
	/**
	 * The Constant NAV.
	 */
	public static final String NAV = "nav";
	
	/**
	 * The Constant NOFRAMES.
	 */
	public static final String NOFRAMES = "noframes";
	
	/**
	 * The Constant NOSCRIPT.
	 */
	public static final String NOSCRIPT = "noscript";
	
	/**
	 * The Constant OBJECT.
	 */
	public static final String OBJECT = "object";
	
	/**
	 * The Constant OL.
	 */
	public static final String OL = "ol";
	
	/**
	 * The Constant OPTGROUP.
	 */
	public static final String OPTGROUP = "optgroup";
	
	/**
	 * The Constant OPTION.
	 */
	public static final String OPTION = "option";
	
	/**
	 * The Constant OUTPUT.
	 */
	public static final String OUTPUT = "output";
	
	/**
	 * The Constant P.
	 */
	public static final String P = "p";
	
	/**
	 * The Constant PARAM.
	 */
	public static final String PARAM = "param";
	
	/**
	 * The Constant PRE.
	 */
	public static final String PRE = "pre";
	
	/**
	 * The Constant PROGRESS.
	 */
	public static final String PROGRESS = "progress";
	
	/**
	 * The Constant Q.
	 */
	public static final String Q = "q";
	
	/**
	 * The Constant RP.
	 */
	public static final String RP = "rp";
	
	/**
	 * The Constant RT.
	 */
	public static final String RT = "rt";
	
	/**
	 * The Constant RUBY.
	 */
	public static final String RUBY = "ruby";
	
	/**
	 * The Constant S.
	 */
	public static final String S = "s";
	
	/**
	 * The Constant SAMP.
	 */
	public static final String SAMP = "samp";
	
	/**
	 * The Constant SCRIPT.
	 */
	public static final String SCRIPT = "script";
	
	/**
	 * The Constant SECTION.
	 */
	public static final String SECTION = "section";
	
	/**
	 * The Constant SELECT.
	 */
	public static final String SELECT = "select";
	
	/**
	 * The Constant SMALL.
	 */
	public static final String SMALL = "small";
	
	/**
	 * The Constant SOURCE.
	 */
	public static final String SOURCE = "source";
	
	/**
	 * The Constant SPAN.
	 */
	public static final String SPAN = "span";
	
	/**
	 * The Constant STRIKE.
	 */
	public static final String STRIKE = "strike";
	
	/**
	 * The Constant STRONG.
	 */
	public static final String STRONG = "strong";
	
	/**
	 * The Constant STYLE.
	 */
	public static final String STYLE = "style";
	
	/**
	 * The Constant SUB.
	 */
	public static final String SUB = "sub";
	
	/**
	 * The Constant SUMMARY.
	 */
	public static final String SUMMARY = "summary";
	
	/**
	 * The Constant SUP.
	 */
	public static final String SUP = "sup";
	
	/**
	 * The Constant TABLE.
	 */
	public static final String TABLE = "table";
	
	/**
	 * The Constant TBODY.
	 */
	public static final String TBODY = "tbody";
	
	/**
	 * The Constant TD.
	 */
	public static final String TD = "td";
	
	/**
	 * The Constant TEXTAREA.
	 */
	public static final String TEXTAREA = "textarea";
	
	/**
	 * The Constant TFOOT.
	 */
	public static final String TFOOT = "tfoot";
	
	/**
	 * The Constant TH.
	 */
	public static final String TH = "th";
	
	/**
	 * The Constant THEAD.
	 */
	public static final String THEAD = "thead";
	
	/**
	 * The Constant TIME.
	 */
	public static final String TIME = "time";
	
	/**
	 * The Constant TITLE.
	 */
	public static final String TITLE = "title";
	
	/**
	 * The Constant TR.
	 */
	public static final String TR = "tr";
	
	/**
	 * The Constant TRACK.
	 */
	public static final String TRACK = "track";
	
	/**
	 * The Constant TT.
	 */
	public static final String TT = "tt";
	
	/**
	 * The Constant U.
	 */
	public static final String U = "u";
	
	/**
	 * The Constant UL.
	 */
	public static final String UL = "ul";
	
	/**
	 * The Constant VAR.
	 */
	public static final String VAR = "var";
	
	/**
	 * The Constant VIDEO.
	 */
	public static final String VIDEO = "video";
	
	/**
	 * The Constant WBR.
	 */
	public static final String WBR = "wbr";

	private static final String[] ALL_TAGS_ARRAY = { COMMENT, DOCTYPE, A, ABBR, ACRONYM, ADDRESS, APPLET, AREA, ARTICLE, ASIDE,
			AUDIO, B, BASE, BASEFONT, BDI, BDO, BIG, BLOCKQUOTE, BODY, BR, BUTTON, CANVAS, CAPTION, CENTER, CITE, CODE,
			COL, COLGROUP, DATALIST, DD, DEL, DETAILS, DFN, DIALOG, DIR, DIV, DL, DT, EM, EMBED, FIELDSET, FIGCAPTION,
			FIGURE, FONT, FOOTER, FORM, FRAME, FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HEADER, HR, HTML, I, IFRAME, IMG,
			INPUT, INS, KBD, KEYGEN, LABEL, LEGEND, LI, LINK, MAIN, MAP, MARK, MENU, MENUITEM, META, METER, NAV,
			NOFRAMES, NOSCRIPT, OBJECT, OL, OPTGROUP, OPTION, OUTPUT, P, PARAM, PRE, PROGRESS, Q, RP, RT, RUBY, S, SAMP,
			SCRIPT, SECTION, SELECT, SMALL, SOURCE, SPAN, STRIKE, STRONG, STYLE, SUB, SUMMARY, SUP, TABLE, TBODY, TD,
			TEXTAREA, TFOOT, TH, THEAD, TIME, TITLE, TR, TRACK, TT, U, UL, VAR, VIDEO, WBR };

	/**
	 * The Constant ALL_TAGS.
	 */
	public static final List<String> ALL_TAGS = Arrays.asList(ALL_TAGS_ARRAY);
}
