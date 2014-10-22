// Compiled by ClojureScript 0.0-2322
goog.provide('reagent.impl.util');
goog.require('cljs.core');
goog.require('clojure.string');
goog.require('clojure.string');
goog.require('reagent.impl.reactimport');
goog.require('reagent.impl.reactimport');
goog.require('reagent.debug');
reagent.impl.util.is_client = !(((function (){try{return window.document;
}catch (e5593){if((e5593 instanceof Object))
{var e = e5593;return null;
} else
{throw e5593;

}
}})() == null));
reagent.impl.util.React = reagent.impl.reactimport.React;
reagent.impl.util.props = "props";
reagent.impl.util.cljs_level = "cljsLevel";
reagent.impl.util.cljs_argv = "cljsArgv";
reagent.impl.util.js_props = (function js_props(C){return (C[reagent.impl.util.props]);
});
reagent.impl.util.extract_props = (function extract_props(v){var p = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(v,(1),null);if(cljs.core.map_QMARK_(p))
{return p;
} else
{return null;
}
});
reagent.impl.util.extract_children = (function extract_children(v){var p = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(v,(1),null);var first_child = ((((p == null)) || (cljs.core.map_QMARK_(p)))?(2):(1));if((cljs.core.count(v) > first_child))
{return cljs.core.subvec.cljs$core$IFn$_invoke$arity$2(v,first_child);
} else
{return null;
}
});
reagent.impl.util.get_argv = (function get_argv(C){return ((C[reagent.impl.util.props])[reagent.impl.util.cljs_argv]);
});
reagent.impl.util.get_props = (function get_props(C){return reagent.impl.util.extract_props(((C[reagent.impl.util.props])[reagent.impl.util.cljs_argv]));
});
reagent.impl.util.get_children = (function get_children(C){return reagent.impl.util.extract_children(((C[reagent.impl.util.props])[reagent.impl.util.cljs_argv]));
});
reagent.impl.util.reagent_component_QMARK_ = (function reagent_component_QMARK_(C){return !((reagent.impl.util.get_argv(C) == null));
});
reagent.impl.util.memoize_1 = (function memoize_1(f){var mem = (cljs.core.atom.cljs$core$IFn$_invoke$arity$1 ? cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY) : cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY));return ((function (mem){
return (function (arg){var v = cljs.core.get.cljs$core$IFn$_invoke$arity$2((cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(mem) : cljs.core.deref.call(null,mem)),arg);if(!((v == null)))
{return v;
} else
{var ret = (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(arg) : f.call(null,arg));cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(mem,cljs.core.assoc,arg,ret);
return ret;
}
});
;})(mem))
});
reagent.impl.util.dont_camel_case = new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, ["aria",null,"data",null], null), null);
reagent.impl.util.capitalize = (function capitalize(s){if((cljs.core.count(s) < (2)))
{return clojure.string.upper_case(s);
} else
{return (''+cljs.core.str.cljs$core$IFn$_invoke$arity$1(clojure.string.upper_case(cljs.core.subs.cljs$core$IFn$_invoke$arity$3(s,(0),(1))))+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.subs.cljs$core$IFn$_invoke$arity$2(s,(1))));
}
});
reagent.impl.util.dash_to_camel = (function dash_to_camel(dashed){if(typeof dashed === 'string')
{return dashed;
} else
{var name_str = cljs.core.name(dashed);var vec__5595 = clojure.string.split.cljs$core$IFn$_invoke$arity$2(name_str,/-/);var start = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__5595,(0),null);var parts = cljs.core.nthnext(vec__5595,(1));if(cljs.core.truth_((reagent.impl.util.dont_camel_case.cljs$core$IFn$_invoke$arity$1 ? reagent.impl.util.dont_camel_case.cljs$core$IFn$_invoke$arity$1(start) : reagent.impl.util.dont_camel_case.call(null,start))))
{return name_str;
} else
{return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(cljs.core.str,start,cljs.core.map.cljs$core$IFn$_invoke$arity$2(reagent.impl.util.capitalize,parts));
}
}
});

/**
* @constructor
*/
reagent.impl.util.partial_ifn = (function (f,args,p){
this.f = f;
this.args = args;
this.p = p;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 6291457;
})
reagent.impl.util.partial_ifn.cljs$lang$type = true;
reagent.impl.util.partial_ifn.cljs$lang$ctorStr = "reagent.impl.util/partial-ifn";
reagent.impl.util.partial_ifn.cljs$lang$ctorPrWriter = (function (this__4127__auto__,writer__4128__auto__,opt__4129__auto__){return cljs.core._write(writer__4128__auto__,"reagent.impl.util/partial-ifn");
});
reagent.impl.util.partial_ifn.prototype.cljs$core$IHash$_hash$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.hash(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [self__.f,self__.args], null));
});
reagent.impl.util.partial_ifn.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (_,other){var self__ = this;
var ___$1 = this;return (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(self__.f,other.f)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(self__.args,other.args));
});
reagent.impl.util.partial_ifn.prototype.call = (function() { 
var G__5597__delegate = function (self__,a){var self____$1 = this;var _ = self____$1;var or__3560__auto___5598 = self__.p;if(cljs.core.truth_(or__3560__auto___5598))
{} else
{self__.p = cljs.core.apply.cljs$core$IFn$_invoke$arity$3(cljs.core.partial,self__.f,self__.args);
}
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(self__.p,a);
};
var G__5597 = function (self__,var_args){
var self__ = this;
var a = null;if (arguments.length > 1) {
  a = cljs.core.array_seq(Array.prototype.slice.call(arguments, 1),0);} 
return G__5597__delegate.call(this,self__,a);};
G__5597.cljs$lang$maxFixedArity = 1;
G__5597.cljs$lang$applyTo = (function (arglist__5599){
var self__ = cljs.core.first(arglist__5599);
var a = cljs.core.rest(arglist__5599);
return G__5597__delegate(self__,a);
});
G__5597.cljs$core$IFn$_invoke$arity$variadic = G__5597__delegate;
return G__5597;
})()
;
reagent.impl.util.partial_ifn.prototype.apply = (function (self__,args5596){var self__ = this;
var self____$1 = this;return self____$1.call.apply(self____$1,[self____$1].concat(cljs.core.aclone(args5596)));
});
reagent.impl.util.partial_ifn.prototype.cljs$core$IFn$_invoke$arity$2 = (function() { 
var G__5600__delegate = function (a){var _ = this;var or__3560__auto___5601 = self__.p;if(cljs.core.truth_(or__3560__auto___5601))
{} else
{self__.p = cljs.core.apply.cljs$core$IFn$_invoke$arity$3(cljs.core.partial,self__.f,self__.args);
}
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(self__.p,a);
};
var G__5600 = function (var_args){
var self__ = this;
var a = null;if (arguments.length > 0) {
  a = cljs.core.array_seq(Array.prototype.slice.call(arguments, 0),0);} 
return G__5600__delegate.call(this,a);};
G__5600.cljs$lang$maxFixedArity = 0;
G__5600.cljs$lang$applyTo = (function (arglist__5602){
var a = cljs.core.seq(arglist__5602);
return G__5600__delegate(a);
});
G__5600.cljs$core$IFn$_invoke$arity$variadic = G__5600__delegate;
return G__5600;
})()
;
reagent.impl.util.__GT_partial_ifn = (function __GT_partial_ifn(f,args,p){return (new reagent.impl.util.partial_ifn(f,args,p));
});
reagent.impl.util.clj_ifn_QMARK_ = (function clj_ifn_QMARK_(x){var or__3560__auto__ = cljs.core.ifn_QMARK_(x);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var G__5606 = x;if(G__5606)
{var bit__4210__auto__ = (G__5606.cljs$lang$protocol_mask$partition1$ & (256));if((bit__4210__auto__) || (G__5606.cljs$core$IMultiFn$))
{return true;
} else
{if((!G__5606.cljs$lang$protocol_mask$partition1$))
{return cljs.core.native_satisfies_QMARK_(cljs.core.IMultiFn,G__5606);
} else
{return false;
}
}
} else
{return cljs.core.native_satisfies_QMARK_(cljs.core.IMultiFn,G__5606);
}
}
});
reagent.impl.util.merge_class = (function merge_class(p1,p2){var class$ = (function (){var temp__4126__auto__ = cljs.core.constant$keyword$45.cljs$core$IFn$_invoke$arity$1(p1);if(cljs.core.truth_(temp__4126__auto__))
{var c1 = temp__4126__auto__;var temp__4126__auto____$1 = cljs.core.constant$keyword$45.cljs$core$IFn$_invoke$arity$1(p2);if(cljs.core.truth_(temp__4126__auto____$1))
{var c2 = temp__4126__auto____$1;return (''+cljs.core.str.cljs$core$IFn$_invoke$arity$1(c1)+" "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(c2));
} else
{return null;
}
} else
{return null;
}
})();if((class$ == null))
{return p2;
} else
{return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(p2,cljs.core.constant$keyword$45,class$);
}
});
reagent.impl.util.merge_style = (function merge_style(p1,p2){var style = (function (){var temp__4126__auto__ = cljs.core.constant$keyword$46.cljs$core$IFn$_invoke$arity$1(p1);if(cljs.core.truth_(temp__4126__auto__))
{var s1 = temp__4126__auto__;var temp__4126__auto____$1 = cljs.core.constant$keyword$46.cljs$core$IFn$_invoke$arity$1(p2);if(cljs.core.truth_(temp__4126__auto____$1))
{var s2 = temp__4126__auto____$1;return cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([s1,s2], 0));
} else
{return null;
}
} else
{return null;
}
})();if((style == null))
{return p2;
} else
{return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(p2,cljs.core.constant$keyword$46,style);
}
});
reagent.impl.util.merge_props = (function merge_props(p1,p2){if((p1 == null))
{return p2;
} else
{if(cljs.core.map_QMARK_(p1))
{} else
{throw (new Error(("Assert failed: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.list(new cljs.core.Symbol(null,"map?","map?",-1780568534,null),new cljs.core.Symbol(null,"p1","p1",703771573,null))], 0))))));
}
return reagent.impl.util.merge_style(p1,reagent.impl.util.merge_class(p1,cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([p1,p2], 0))));
}
});
reagent.impl.util._not_found = (function (){var obj5608 = {};return obj5608;
})();
reagent.impl.util.identical_ish_QMARK_ = (function identical_ish_QMARK_(x,y){return (cljs.core.keyword_identical_QMARK_(x,y)) || ((((x instanceof cljs.core.Symbol)) || ((cljs.core.type(x) === reagent.impl.util.partial_ifn))) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(x,y)));
});
reagent.impl.util.shallow_equal_maps = (function shallow_equal_maps(x,y){var or__3560__auto__ = (x === y);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var and__3548__auto__ = cljs.core.map_QMARK_(x);if(and__3548__auto__)
{var and__3548__auto____$1 = cljs.core.map_QMARK_(y);if(and__3548__auto____$1)
{var and__3548__auto____$2 = (cljs.core.count(x) === cljs.core.count(y));if(and__3548__auto____$2)
{return cljs.core.reduce_kv(((function (and__3548__auto____$2,and__3548__auto____$1,and__3548__auto__,or__3560__auto__){
return (function (res,k,v){var yv = cljs.core.get.cljs$core$IFn$_invoke$arity$3(y,k,reagent.impl.util._not_found);if(cljs.core.truth_((function (){var or__3560__auto____$1 = (v === yv);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{var or__3560__auto____$2 = reagent.impl.util.identical_ish_QMARK_(v,yv);if(or__3560__auto____$2)
{return or__3560__auto____$2;
} else
{var and__3548__auto____$3 = cljs.core.keyword_identical_QMARK_(k,cljs.core.constant$keyword$46);if(and__3548__auto____$3)
{return shallow_equal_maps(v,yv);
} else
{return and__3548__auto____$3;
}
}
}
})()))
{return res;
} else
{return cljs.core.reduced(false);
}
});})(and__3548__auto____$2,and__3548__auto____$1,and__3548__auto__,or__3560__auto__))
,true,x);
} else
{return and__3548__auto____$2;
}
} else
{return and__3548__auto____$1;
}
} else
{return and__3548__auto__;
}
}
});
reagent.impl.util.equal_args = (function equal_args(v1,v2){if(cljs.core.vector_QMARK_(v1))
{} else
{throw (new Error(("Assert failed: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.list(new cljs.core.Symbol(null,"vector?","vector?",-61367869,null),new cljs.core.Symbol(null,"v1","v1",-2141311508,null))], 0))))));
}
if(cljs.core.vector_QMARK_(v2))
{} else
{throw (new Error(("Assert failed: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.list(new cljs.core.Symbol(null,"vector?","vector?",-61367869,null),new cljs.core.Symbol(null,"v2","v2",1875554983,null))], 0))))));
}
var or__3560__auto__ = (v1 === v2);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var and__3548__auto__ = (cljs.core.count(v1) === cljs.core.count(v2));if(and__3548__auto__)
{return cljs.core.reduce_kv(((function (and__3548__auto__,or__3560__auto__){
return (function (res,k,v){var v_SINGLEQUOTE_ = cljs.core.nth.cljs$core$IFn$_invoke$arity$2(v2,k);if(cljs.core.truth_((function (){var or__3560__auto____$1 = (v === v_SINGLEQUOTE_);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{var or__3560__auto____$2 = reagent.impl.util.identical_ish_QMARK_(v,v_SINGLEQUOTE_);if(or__3560__auto____$2)
{return or__3560__auto____$2;
} else
{var and__3548__auto____$1 = cljs.core.map_QMARK_(v);if(and__3548__auto____$1)
{return reagent.impl.util.shallow_equal_maps(v,v_SINGLEQUOTE_);
} else
{return and__3548__auto____$1;
}
}
}
})()))
{return res;
} else
{return cljs.core.reduced(false);
}
});})(and__3548__auto__,or__3560__auto__))
,true,v1);
} else
{return and__3548__auto__;
}
}
});
