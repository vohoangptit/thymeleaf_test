; (function () {
    'use strict';

    var NoticeRegist = {
        validate: function () {

            $('#frm').validate({
                groups: {
                    methodGroup: 'settingNoticeMethodPortal settingNoticeMethodMail'
                },
                rules: {
                    settingNoticeMethodPortal: {
                        require_from_group: [1, ".method-group"]
                    },
                    settingNoticeMethodMail: {
                        require_from_group: [1, ".method-group"]
                    },
                    contentTitleTemplate: {
                        required: true,
                        rangelength: [1, 256]
                    },
                    contentTextTemplate: {
                        required: true,
                        rangelength: [1, 2048]
                    }
                },
                messages: {
                    settingNoticeMethodMail: gbs.messages.settingNoticeMethod,
                    settingNoticeMethodPortal: gbs.messages.settingNoticeMethod,
                    contentTitleTemplate: {
                        required: gbs.messages.requiredErrorMessage,
                        rangelength: gbs.messages.rangeLengthErrorMessage
                    },
                    contentTextTemplate: {
                        required: gbs.messages.requiredErrorMessage,
                        rangelength: gbs.messages.rangeLengthErrorMessage
                    }
                }
            });
        },

        getFormData: function () {
            var portal = $('#settingNoticeMethodPortal').is(':checked'),
                mail = $('#settingNoticeMethodMail').is(':checked'),
                displayTarget = (portal && mail) ? 3 : (mail ? 2 : 1),
                data = {
                    displayDay: $('#settingDisplayRange').val(),
                    title: $('#contentTitleTemplate').val(),
                    notice: $('#contentTextTemplate').val(),
                    displayTarget: displayTarget,
                    classId: 1,
                    settlementWayId: 1
                },
                result = [];

            result.data = data;
            result.portal = portal;
            result.mail = mail;
            return result;
        },

        handlingSelectNoticeContentOnClick: function () {
            $('.btn-select').on('click', function () {
                // Because the template name must be chosen when the button 「選択」 on click
                // There to, its field not required when register new notice
                // So should be added the rule of validation dynamic for its field before processing
                $('#settingTemplate').rules('add', {
                    required: true,
                    messages: {
                        required: gbs.messages.requirePleaseSelect
                    }
                });

                // Template select only check when the button 「選択」 on click
                // Everytime user on click, the validate of this element will be added by dynamic
                // Hence after checked valid, should be remove rules of out this element
                $('#settingTemplate').on('change', function (e) {
                    var target = $(e.target),
                        ariaInvalid = target.attr('aria-invalid');
                    if (ariaInvalid.length) {
                        $('#settingTemplate').rules('remove');
                    }
                });

                if ($('#frm').validate().element('#settingTemplate')) {
                    var templateName = $('#settingTemplate').find('option:selected').text();
                    $.ajax(gbs.ajax.api.get("/notice-template/" + templateName)).then(function (data) {
                        $('#contentTitleTemplate').val(data.title).change();
                        $('#contentTextTemplate').val(data.notice).change();
                    }).fail(function (xhr) {
                        gbs.announceApiErrorResponse(xhr);
                    });
                }
            });
        },

        handlingRegistNoticeOnClick: function () {
            var self = this;
            $('.btn-regist').on('click', function (event) {
                event.preventDefault();
                // Since registed the notice not required template name selected
                // It's ignoring when checking validation
                $('#frm').validate().resetForm();
                $('#settingTemplate').rules('remove');
                var data = self.getFormData();
                if ($('#frm').valid()) {
                    if (data.mail) {
                        $('#registNoticeConfirmDialog').modal('show');
                    } else {
                        $.ajax(gbs.ajax.api.post('/notice/save', data.data)).then(function () {
                            gbs.showInfoNotify(gbs.messages.showInfoNotify);
                            // Clear all inputs data
                            $("#frm")[0].reset();
                        }).fail(function (xhr) {
                            gbs.announceApiErrorResponse(xhr);
                        });
                    }
                }
            });
        },

        handlingSendMailNoticeOnClick: function () {
            var self = this;
            $('.btn-send').on('click', function (event) {
                event.preventDefault();
                var data = self.getFormData();
                if ($('#frm').valid()) {
                    if (data.portal && data.mail || data.mail) {
                        $.ajax(gbs.ajax.api.post('/notice/save', data.data)).then(function (_data) {
                            $.ajax(gbs.ajax.api.get('/notice/newest/')).then(function (noticeData) {
                                $.ajax(gbs.ajax.api.post('/notice-mail/save', { noticeId: noticeData.id })).then(function () {
                                    $('#registTransmissionCompletedDialog').modal('show');
                                    $('#registTransmissionCompletedDialog').find('#titleName').text($('#contentTitleTemplate').val());
                                }).fail(function (xhr) {
                                    gbs.announceApiErrorResponse(xhr);
                                });
                            }).fail(function (xhr) {
                                gbs.announceApiErrorResponse(xhr);
                            });
                        }).fail(function (xhr) {
                            gbs.announceApiErrorResponse(xhr);
                        });
                    }
                }
            });
        },

        handlingSpinnerText: function () {
            $('.spinner .btn:first-of-type').on('click', function () {
                var btn = $(this),
                    input = btn.closest('.spinner').find('input');
                if (input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max'))) {
                    input.val(parseInt(input.val(), 10) + 1);
                } else {
                    btn.next("disabled", true);
                }
            });

            $('.spinner .btn:last-of-type').on('click', function () {
                var btn = $(this),
                    input = btn.closest('.spinner').find('input');
                if (input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min'))) {
                    input.val(parseInt(input.val(), 10) - 1);
                } else {
                    btn.prev("disabled", true);
                }
            });

            $('.spinner input[type=text]').on('focus', function () {
                this.select();
            });
        },

        setup: function () {
            this.validate();
        },

        run: function () {
            this.setup();
            this.handlingSpinnerText();
            this.handlingSelectNoticeContentOnClick();
            this.handlingRegistNoticeOnClick();
            this.handlingSendMailNoticeOnClick();
        }
    }

    $(document).ready(function () {
        NoticeRegist.run();
    });
})();