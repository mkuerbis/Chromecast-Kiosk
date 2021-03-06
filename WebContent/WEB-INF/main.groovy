import de.michaelkuerbis.presenter.servlets.SettingsServlet;
import de.michaelkuerbis.presenter.utils.CastConnection;

html.div {
	div( class:"container", id:"main"){
		div(class:"col-xs-12"){
			div( class:"page-header"){
				h1(class:"pull-left",'data-i18n':"main_title",'Overview')
				//TODO Modal dialog
				div(style:"margin-left: 10px;padding-top: 20px;"){
					button(class:"btn btn-default", 'data-toggle':"modal", 'data-target':"#addchromecast", "+")
					button(class:"btn btn-default", id:"refreshall", style:"height: 34px;"){
						span(class:"glyphicon glyphicon-refresh", " ")
					}
					button(class:"btn btn-default",id:"searchcasts", 'data-toggle':"modal", 'data-target':"#discoverchromecast", style:"height: 34px;"){
						span(class:"glyphicon glyphicon-search", " ")
					}
				}

			}
		}

		if(SettingsServlet.getConnections().size() == 0){
			div(class:"col-xs-12 alert alert-warning"){
				div(class:"pull-left",'data-i18n':"main_no_casts","There are no chromecasts defined.")
				a(href:"#",'data-toggle':"modal", 'data-target':"#addchromecast",'data-i18n':"main_no_casts_add_one", "add one.")
			}
		}else{
			for(CastConnection con: SettingsServlet.getConnections()){
				div(class:"col-xs-12 col-sm-6 col-sm-4 col-lg-3 chromecastpanel", 'data-ip': con.getIp()){
					div(class:"panel panel-default"){
						div(class:"panel-heading"){
							h3(class:"panel-title", con.getName()){
								button(class:"btn btn-default btn-xs panelrefresh", style:"min-height: 28px;"){
									span(class:"glyphicon glyphicon-refresh", 'aria-hidden':"true")
								}

								button(class:"btn btn-default btn-xs paneltrash btn-warning pull-right", style:"min-height: 28px;"){
									span(class:"glyphicon glyphicon-trash", 'aria-hidden':"true")
								}
							}
						}
						div(class:"panel-body"){
							p("IP: "+con.getIp())
							div{
								p('data-i18n':"main_panel_status","Status: ")
								p(class:"status")
								p('data-i18n':"main_panel_app","App:")
								p(class:"application")
								div(class:"btn-group toogle-option", 'data-toggle':"buttons"){
									a(href:"#",class:"btn btn-primary option-default "+(con.isDefault()?"active":""),'data-ip':con.getIp(),"default"){
										if(con.isDefault()){
											input( type:"radio", name:"options", autocomplete:"off", checked:"")
										}else{
											input( type:"radio", name:"options", autocomplete:"off")
										}
									}
									a(href:"#",class:"btn btn-primary option-info "+(!con.isDefault()?"active":""),'data-ip':con.getIp(),"info"){
										if(!con.isDefault()){
											input( type:"radio", name:"options", autocomplete:"off", checked:"")
										}else{
											input( type:"radio", name:"options", autocomplete:"off")
										}
									}
								}
							}
							div(class:"alert alert-warning trashmsg", style:"display:none;"){
								p(class:"text-center",'data-i18n':"main_panel_remove", "Remove this chromecast?")
								p(class:"text-center"){
									a(href:"#", class:"btn btn-danger yestrash",'data-i18n':"main_panel_remove_yes",'data-ip':con.getIp(), "yes")
									a(href:"#", class:"btn btn-default notrash",'data-i18n':"main_panel_remove_no", "no")
								}
							}
						}
					}
				}
			}
		}

		div( class:"modal fade", id:"addchromecast", tabindex:"-1", role:"dialog", 'aria-labelledby':"myModalLabel"){
			div (class:"modal-dialog", role:"document"){
				div (class:"modal-content"){
					div (class:"modal-header"){
						button( type:"button", class:"close", 'data-dismiss':"modal", 'aria-label':"Close"){
							span( 'aria-hidden':"true", "x")
						}
						h4( class:"modal-title", id:"myModalLabel",'data-i18n':"main_add_title", "Add Chromecast")
					}
					div (class:"modal-body"){
						div(class:"form-horizontal"){
							div( class:"form-group"){
								label(class:"col-sm-2 control-label",'data-i18n':"main_add_name", "Name:")
								div( class:"col-sm-10"){
									input( type:"text",class:"form-control", id:"addname",'data-i18n-placeholder':"main_add_name_placeholder", placeholder:"Chromecast #1")
								}
							}
							div( class:"form-group"){
								label(class:"col-sm-2 control-label",'data-i18n':"main_add_ip", "Ip:")
								div( class:"col-sm-10"){
									input( type:"text",class:"form-control", id:"addip",'data-i18n-placeholder':"main_add_ip_placeholder", placeholder:"127.0.0.1")
								}
							}
						}
					}
					div (class:"modal-footer"){
						button( type:"button", id:"addcastbutton", class:"btn btn-primary",'data-i18n':"main_add_button", "add Chromecast")
					}
				}
			}
		}
		div( class:"modal fade", id:"discoverchromecast", tabindex:"-1", role:"dialog", 'aria-labelledby':"myModalLabel"){
			div (class:"modal-dialog", role:"document"){
				div (class:"modal-content"){
					div (class:"modal-header"){
						button( type:"button", class:"close", 'data-dismiss':"modal", 'aria-label':"Close"){
							span( 'aria-hidden':"true", "x")
						}
						h4( class:"modal-title", id:"myModalLabel",'data-i18n':"main_discover_title", "discovered Chromecasts")
					}
					div (class:"modal-body"){ h1("fooo") }
				}
			}
		}

	}
}