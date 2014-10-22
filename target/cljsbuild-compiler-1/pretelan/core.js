// Compiled by ClojureScript 0.0-2322
goog.provide('pretelan.core');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('ajax.core');
goog.require('ajax.core');
goog.require('reagent.core');
goog.require('reagent.core');
pretelan.core.page = reagent.core.atom.cljs$core$IFn$_invoke$arity$1("");
pretelan.core.selid = (function selid(id){return document.getElementById(id);
});
pretelan.core.answer_form = (function answer_form(){return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$61,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.constant$keyword$45,"large-4 large-centered"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$62,new cljs.core.PersistentArrayMap(null, 2, [cljs.core.constant$keyword$63,"text",cljs.core.constant$keyword$19,"beliga"], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$64,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.constant$keyword$65,(function (){return window.alert("Wow cool!!");
})], null),"Submit"], null)], null);
});
pretelan.core.login_callback = (function login_callback(response){return window.alert(response);
});
pretelan.core.login_act = (function login_act(username,password){return ajax.core.POST.cljs$core$IFn$_invoke$arity$variadic("/login-act",cljs.core.array_seq([new cljs.core.PersistentArrayMap(null, 2, [cljs.core.constant$keyword$35,new cljs.core.PersistentArrayMap(null, 2, [cljs.core.constant$keyword$66,username,cljs.core.constant$keyword$67,password], null),cljs.core.constant$keyword$37,pretelan.core.login_callback], null)], 0));
});
pretelan.core.login_form = (function login_form(){var email = reagent.core.atom.cljs$core$IFn$_invoke$arity$1("");var password = reagent.core.atom.cljs$core$IFn$_invoke$arity$1("");return ((function (email,password){
return (function (){return new cljs.core.PersistentVector(null, 7, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$68,new cljs.core.PersistentArrayMap(null, 1, [cljs.core.constant$keyword$45,"zpanel3"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$69,"Login bukan lojin"], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$70], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$62,new cljs.core.PersistentArrayMap(null, 5, [cljs.core.constant$keyword$63,"text",cljs.core.constant$keyword$71,(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(email) : cljs.core.deref.call(null,email)),cljs.core.constant$keyword$19,"userEmail",cljs.core.constant$keyword$72,((function (email,password){
return (function (p1__5242_SHARP_){return (cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(email,p1__5242_SHARP_.target.value) : cljs.core.reset_BANG_.call(null,email,p1__5242_SHARP_.target.value));
});})(email,password))
,cljs.core.constant$keyword$73,"email as unique identifier"], null)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$62,new cljs.core.PersistentArrayMap(null, 5, [cljs.core.constant$keyword$63,"password",cljs.core.constant$keyword$71,(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(password) : cljs.core.deref.call(null,password)),cljs.core.constant$keyword$19,"userPassword",cljs.core.constant$keyword$72,((function (email,password){
return (function (p1__5243_SHARP_){return (cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2 ? cljs.core.reset_BANG_.cljs$core$IFn$_invoke$arity$2(password,p1__5243_SHARP_.target.value) : cljs.core.reset_BANG_.call(null,password,p1__5243_SHARP_.target.value));
});})(email,password))
,cljs.core.constant$keyword$73,"password"], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.constant$keyword$64,new cljs.core.PersistentArrayMap(null, 3, [cljs.core.constant$keyword$45,"small right",cljs.core.constant$keyword$19,"loginButton",cljs.core.constant$keyword$65,((function (email,password){
return (function (){return pretelan.core.login_act((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(email) : cljs.core.deref.call(null,email)),(cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(password) : cljs.core.deref.call(null,password)));
});})(email,password))
], null),"Login"], null)], null);
});
;})(email,password))
});
pretelan.core.start = (function start(){if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(pretelan.core.page) : cljs.core.deref.call(null,pretelan.core.page)),"login"))
{return reagent.core.render_component.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [pretelan.core.login_form], null),pretelan.core.selid("loginForm"));
} else
{if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(pretelan.core.page) : cljs.core.deref.call(null,pretelan.core.page)),"problem"))
{return reagent.core.render_component.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [pretelan.core.answer_form], null),pretelan.core.selid("main"));
} else
{return null;
}
}
});
pretelan.core.start();
