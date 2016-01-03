/*
 * jQuery Mobile Framework : plugin to provide a date and time picker.
 * Copyright (c) JTSage
 * CC 3.0 Attribution.  May be relicensed without permission/notifcation.
 * https://github.com/jtsage/jquery-mobile-datebox
 *
 * Translation by: Unknown
 *
 */

jQuery.extend(jQuery.mobile.datebox.prototype.options.lang, {
	'ko': {
		setDateButtonLabel: "닫기",
		setTimeButtonLabel: "닫기",
		setDurationButtonLabel: "닫기",
		calTodayButtonLabel: "﻿오늘 이동",
		titleDateDialogLabel: "날짜 선택",
		titleTimeDialogLabel: "시간 선택",
		daysOfWeek: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
		daysOfWeekShort: ["일", "월", "화", "수", "목", "금", "토"],
		monthsOfYear: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		monthsOfYearShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		durationLabel: ["날짜", "시간", "분", "초"],
		durationDays: ["하루", "날짜"],
		tooltip: "﻿오픈 날짜 선택",
		nextMonth: "﻿다음 달",
		prevMonth: "﻿이전 달",
		timeFormat: 24,
		headerFormat: '%A, %B %-d, %Y',
		dateFieldOrder: ['y','m','d'],
		timeFieldOrder: ['h', 'i', 'a'],
		slideFieldOrder: ['y', 'm', 'd'],
		dateFormat: "%Y-%m-%d",
		useArabicIndic: false,
		isRTL: false,
		calStartDay: 0,
		clearButton: "지우기",
		durationOrder: ['d', 'h', 'i', 's'],
		meridiem: ["AM", "PM"],
		timeOutput: "%k:%M",
		durationFormat: "%Dd %DA, %Dl:%DM:%DS",
		calDateListLabel: "다른 날짜",
		calHeaderFormat: "%B %Y"
	}
});
jQuery.extend(jQuery.mobile.datebox.prototype.options, {
	useLang: 'ko'
});
