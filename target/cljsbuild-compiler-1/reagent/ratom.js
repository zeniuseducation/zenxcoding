// Compiled by ClojureScript 0.0-2322
goog.provide('reagent.ratom');
goog.require('cljs.core');
reagent.ratom.debug = false;
reagent.ratom._running = (cljs.core.atom.cljs$core$IFn$_invoke$arity$1 ? cljs.core.atom.cljs$core$IFn$_invoke$arity$1((0)) : cljs.core.atom.call(null,(0)));
reagent.ratom.running = (function running(){return (cljs.core.deref.cljs$core$IFn$_invoke$arity$1 ? cljs.core.deref.cljs$core$IFn$_invoke$arity$1(reagent.ratom._running) : cljs.core.deref.call(null,reagent.ratom._running));
});
reagent.ratom.capture_derefed = (function capture_derefed(f,obj){obj.cljsCaptured = null;
var _STAR_ratom_context_STAR_5501 = reagent.ratom._STAR_ratom_context_STAR_;try{reagent.ratom._STAR_ratom_context_STAR_ = obj;
return (f.cljs$core$IFn$_invoke$arity$0 ? f.cljs$core$IFn$_invoke$arity$0() : f.call(null));
}finally {reagent.ratom._STAR_ratom_context_STAR_ = _STAR_ratom_context_STAR_5501;
}});
reagent.ratom.captured = (function captured(obj){var c = obj.cljsCaptured;obj.cljsCaptured = null;
return c;
});
reagent.ratom.notify_deref_watcher_BANG_ = (function notify_deref_watcher_BANG_(derefable){var obj = reagent.ratom._STAR_ratom_context_STAR_;if((obj == null))
{return null;
} else
{var captured = obj.cljsCaptured;return obj.cljsCaptured = cljs.core.conj.cljs$core$IFn$_invoke$arity$2((((captured == null))?cljs.core.PersistentHashSet.EMPTY:captured),derefable);
}
});

/**
* @constructor
*/
reagent.ratom.RAtom = (function (state,meta,validator,watches){
this.state = state;
this.meta = meta;
this.validator = validator;
this.watches = watches;
this.cljs$lang$protocol_mask$partition0$ = 2153938944;
this.cljs$lang$protocol_mask$partition1$ = 114690;
})
reagent.ratom.RAtom.cljs$lang$type = true;
reagent.ratom.RAtom.cljs$lang$ctorStr = "reagent.ratom/RAtom";
reagent.ratom.RAtom.cljs$lang$ctorPrWriter = (function (this__4127__auto__,writer__4128__auto__,opt__4129__auto__){return cljs.core._write(writer__4128__auto__,"reagent.ratom/RAtom");
});
reagent.ratom.RAtom.prototype.cljs$core$IHash$_hash$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;return goog.getUid(this$__$1);
});
reagent.ratom.RAtom.prototype.cljs$core$IWatchable$_notify_watches$arity$3 = (function (this$,oldval,newval){var self__ = this;
var this$__$1 = this;return cljs.core.reduce_kv(((function (this$__$1){
return (function (_,key,f){(f.cljs$core$IFn$_invoke$arity$4 ? f.cljs$core$IFn$_invoke$arity$4(key,this$__$1,oldval,newval) : f.call(null,key,this$__$1,oldval,newval));
return null;
});})(this$__$1))
,null,self__.watches);
});
reagent.ratom.RAtom.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,f){var self__ = this;
var this$__$1 = this;return self__.watches = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.watches,key,f);
});
reagent.ratom.RAtom.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (this$,key){var self__ = this;
var this$__$1 = this;return self__.watches = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.watches,key);
});
reagent.ratom.RAtom.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (a,writer,opts){var self__ = this;
var a__$1 = this;cljs.core._write(writer,"#<Atom: ");
cljs.core.pr_writer(self__.state,writer,opts);
return cljs.core._write(writer,">");
});
reagent.ratom.RAtom.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return self__.meta;
});
reagent.ratom.RAtom.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (a,f){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(self__.state) : f.call(null,self__.state)));
});
reagent.ratom.RAtom.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (a,f,x){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f.cljs$core$IFn$_invoke$arity$2 ? f.cljs$core$IFn$_invoke$arity$2(self__.state,x) : f.call(null,self__.state,x)));
});
reagent.ratom.RAtom.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (a,f,x,y){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f.cljs$core$IFn$_invoke$arity$3 ? f.cljs$core$IFn$_invoke$arity$3(self__.state,x,y) : f.call(null,self__.state,x,y)));
});
reagent.ratom.RAtom.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (a,f,x,y,more){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,cljs.core.apply.cljs$core$IFn$_invoke$arity$5(f,self__.state,x,y,more));
});
reagent.ratom.RAtom.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (a,new_value){var self__ = this;
var a__$1 = this;if((self__.validator == null))
{} else
{if(cljs.core.truth_((self__.validator.cljs$core$IFn$_invoke$arity$1 ? self__.validator.cljs$core$IFn$_invoke$arity$1(new_value) : self__.validator.call(null,new_value))))
{} else
{throw (new Error(("Assert failed: Validator rejected reference state\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.list(new cljs.core.Symbol(null,"validator","validator",-325659154,null),new cljs.core.Symbol(null,"new-value","new-value",-1567397401,null))], 0))))));
}
}
var old_value = self__.state;self__.state = new_value;
if((self__.watches == null))
{} else
{cljs.core._notify_watches(a__$1,old_value,new_value);
}
return new_value;
});
reagent.ratom.RAtom.prototype.cljs$core$IDeref$_deref$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;reagent.ratom.notify_deref_watcher_BANG_(this$__$1);
return self__.state;
});
reagent.ratom.RAtom.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (o,other){var self__ = this;
var o__$1 = this;return (o__$1 === other);
});
reagent.ratom.__GT_RAtom = (function __GT_RAtom(state,meta,validator,watches){return (new reagent.ratom.RAtom(state,meta,validator,watches));
});
/**
* Like clojure.core/atom, except that it keeps track of derefs.
* @param {...*} var_args
*/
reagent.ratom.atom = (function() {
var atom = null;
var atom__1 = (function (x){return (new reagent.ratom.RAtom(x,null,null,null));
});
var atom__2 = (function() { 
var G__5505__delegate = function (x,p__5502){var map__5504 = p__5502;var map__5504__$1 = ((cljs.core.seq_QMARK_(map__5504))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__5504):map__5504);var validator = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5504__$1,cljs.core.constant$keyword$8);var meta = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5504__$1,cljs.core.constant$keyword$3);return (new reagent.ratom.RAtom(x,meta,validator,null));
};
var G__5505 = function (x,var_args){
var p__5502 = null;if (arguments.length > 1) {
  p__5502 = cljs.core.array_seq(Array.prototype.slice.call(arguments, 1),0);} 
return G__5505__delegate.call(this,x,p__5502);};
G__5505.cljs$lang$maxFixedArity = 1;
G__5505.cljs$lang$applyTo = (function (arglist__5506){
var x = cljs.core.first(arglist__5506);
var p__5502 = cljs.core.rest(arglist__5506);
return G__5505__delegate(x,p__5502);
});
G__5505.cljs$core$IFn$_invoke$arity$variadic = G__5505__delegate;
return G__5505;
})()
;
atom = function(x,var_args){
var p__5502 = var_args;
switch(arguments.length){
case 1:
return atom__1.call(this,x);
default:
return atom__2.cljs$core$IFn$_invoke$arity$variadic(x, cljs.core.array_seq(arguments, 1));
}
throw(new Error('Invalid arity: ' + arguments.length));
};
atom.cljs$lang$maxFixedArity = 1;
atom.cljs$lang$applyTo = atom__2.cljs$lang$applyTo;
atom.cljs$core$IFn$_invoke$arity$1 = atom__1;
atom.cljs$core$IFn$_invoke$arity$variadic = atom__2.cljs$core$IFn$_invoke$arity$variadic;
return atom;
})()
;
reagent.ratom.IDisposable = (function (){var obj5508 = {};return obj5508;
})();
reagent.ratom.dispose_BANG_ = (function dispose_BANG_(this$){if((function (){var and__3548__auto__ = this$;if(and__3548__auto__)
{return this$.reagent$ratom$IDisposable$dispose_BANG_$arity$1;
} else
{return and__3548__auto__;
}
})())
{return this$.reagent$ratom$IDisposable$dispose_BANG_$arity$1(this$);
} else
{var x__4187__auto__ = (((this$ == null))?null:this$);return (function (){var or__3560__auto__ = (reagent.ratom.dispose_BANG_[goog.typeOf(x__4187__auto__)]);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var or__3560__auto____$1 = (reagent.ratom.dispose_BANG_["_"]);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{throw cljs.core.missing_protocol("IDisposable.dispose!",this$);
}
}
})().call(null,this$);
}
});
reagent.ratom.IRunnable = (function (){var obj5510 = {};return obj5510;
})();
reagent.ratom.run = (function run(this$){if((function (){var and__3548__auto__ = this$;if(and__3548__auto__)
{return this$.reagent$ratom$IRunnable$run$arity$1;
} else
{return and__3548__auto__;
}
})())
{return this$.reagent$ratom$IRunnable$run$arity$1(this$);
} else
{var x__4187__auto__ = (((this$ == null))?null:this$);return (function (){var or__3560__auto__ = (reagent.ratom.run[goog.typeOf(x__4187__auto__)]);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var or__3560__auto____$1 = (reagent.ratom.run["_"]);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{throw cljs.core.missing_protocol("IRunnable.run",this$);
}
}
})().call(null,this$);
}
});
reagent.ratom.IComputedImpl = (function (){var obj5512 = {};return obj5512;
})();
reagent.ratom._update_watching = (function _update_watching(this$,derefed){if((function (){var and__3548__auto__ = this$;if(and__3548__auto__)
{return this$.reagent$ratom$IComputedImpl$_update_watching$arity$2;
} else
{return and__3548__auto__;
}
})())
{return this$.reagent$ratom$IComputedImpl$_update_watching$arity$2(this$,derefed);
} else
{var x__4187__auto__ = (((this$ == null))?null:this$);return (function (){var or__3560__auto__ = (reagent.ratom._update_watching[goog.typeOf(x__4187__auto__)]);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var or__3560__auto____$1 = (reagent.ratom._update_watching["_"]);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{throw cljs.core.missing_protocol("IComputedImpl.-update-watching",this$);
}
}
})().call(null,this$,derefed);
}
});
reagent.ratom._handle_change = (function _handle_change(k,sender,oldval,newval){if((function (){var and__3548__auto__ = k;if(and__3548__auto__)
{return k.reagent$ratom$IComputedImpl$_handle_change$arity$4;
} else
{return and__3548__auto__;
}
})())
{return k.reagent$ratom$IComputedImpl$_handle_change$arity$4(k,sender,oldval,newval);
} else
{var x__4187__auto__ = (((k == null))?null:k);return (function (){var or__3560__auto__ = (reagent.ratom._handle_change[goog.typeOf(x__4187__auto__)]);if(or__3560__auto__)
{return or__3560__auto__;
} else
{var or__3560__auto____$1 = (reagent.ratom._handle_change["_"]);if(or__3560__auto____$1)
{return or__3560__auto____$1;
} else
{throw cljs.core.missing_protocol("IComputedImpl.-handle-change",k);
}
}
})().call(null,k,sender,oldval,newval);
}
});
reagent.ratom.call_watches = (function call_watches(obs,watches,oldval,newval){return cljs.core.reduce_kv((function (_,key,f){(f.cljs$core$IFn$_invoke$arity$4 ? f.cljs$core$IFn$_invoke$arity$4(key,obs,oldval,newval) : f.call(null,key,obs,oldval,newval));
return null;
}),null,watches);
});

/**
* @constructor
*/
reagent.ratom.Reaction = (function (f,state,dirty_QMARK_,active_QMARK_,watching,watches,auto_run,on_set,on_dispose){
this.f = f;
this.state = state;
this.dirty_QMARK_ = dirty_QMARK_;
this.active_QMARK_ = active_QMARK_;
this.watching = watching;
this.watches = watches;
this.auto_run = auto_run;
this.on_set = on_set;
this.on_dispose = on_dispose;
this.cljs$lang$protocol_mask$partition0$ = 2153807872;
this.cljs$lang$protocol_mask$partition1$ = 114690;
})
reagent.ratom.Reaction.cljs$lang$type = true;
reagent.ratom.Reaction.cljs$lang$ctorStr = "reagent.ratom/Reaction";
reagent.ratom.Reaction.cljs$lang$ctorPrWriter = (function (this__4127__auto__,writer__4128__auto__,opt__4129__auto__){return cljs.core._write(writer__4128__auto__,"reagent.ratom/Reaction");
});
reagent.ratom.Reaction.prototype.reagent$ratom$IComputedImpl$ = true;
reagent.ratom.Reaction.prototype.reagent$ratom$IComputedImpl$_handle_change$arity$4 = (function (this$,sender,oldval,newval){var self__ = this;
var this$__$1 = this;if(cljs.core.truth_((function (){var and__3548__auto__ = self__.active_QMARK_;if(cljs.core.truth_(and__3548__auto__))
{return (cljs.core.not(self__.dirty_QMARK_)) && (!((oldval === newval)));
} else
{return and__3548__auto__;
}
})()))
{self__.dirty_QMARK_ = true;
return (function (){var or__3560__auto__ = self__.auto_run;if(cljs.core.truth_(or__3560__auto__))
{return or__3560__auto__;
} else
{return reagent.ratom.run;
}
})().call(null,this$__$1);
} else
{return null;
}
});
reagent.ratom.Reaction.prototype.reagent$ratom$IComputedImpl$_update_watching$arity$2 = (function (this$,derefed){var self__ = this;
var this$__$1 = this;var seq__5513_5525 = cljs.core.seq(derefed);var chunk__5514_5526 = null;var count__5515_5527 = (0);var i__5516_5528 = (0);while(true){
if((i__5516_5528 < count__5515_5527))
{var w_5529 = chunk__5514_5526.cljs$core$IIndexed$_nth$arity$2(null,i__5516_5528);if(cljs.core.contains_QMARK_(self__.watching,w_5529))
{} else
{cljs.core.add_watch(w_5529,this$__$1,reagent.ratom._handle_change);
}
{
var G__5530 = seq__5513_5525;
var G__5531 = chunk__5514_5526;
var G__5532 = count__5515_5527;
var G__5533 = (i__5516_5528 + (1));
seq__5513_5525 = G__5530;
chunk__5514_5526 = G__5531;
count__5515_5527 = G__5532;
i__5516_5528 = G__5533;
continue;
}
} else
{var temp__4126__auto___5534 = cljs.core.seq(seq__5513_5525);if(temp__4126__auto___5534)
{var seq__5513_5535__$1 = temp__4126__auto___5534;if(cljs.core.chunked_seq_QMARK_(seq__5513_5535__$1))
{var c__4316__auto___5536 = cljs.core.chunk_first(seq__5513_5535__$1);{
var G__5537 = cljs.core.chunk_rest(seq__5513_5535__$1);
var G__5538 = c__4316__auto___5536;
var G__5539 = cljs.core.count(c__4316__auto___5536);
var G__5540 = (0);
seq__5513_5525 = G__5537;
chunk__5514_5526 = G__5538;
count__5515_5527 = G__5539;
i__5516_5528 = G__5540;
continue;
}
} else
{var w_5541 = cljs.core.first(seq__5513_5535__$1);if(cljs.core.contains_QMARK_(self__.watching,w_5541))
{} else
{cljs.core.add_watch(w_5541,this$__$1,reagent.ratom._handle_change);
}
{
var G__5542 = cljs.core.next(seq__5513_5535__$1);
var G__5543 = null;
var G__5544 = (0);
var G__5545 = (0);
seq__5513_5525 = G__5542;
chunk__5514_5526 = G__5543;
count__5515_5527 = G__5544;
i__5516_5528 = G__5545;
continue;
}
}
} else
{}
}
break;
}
var seq__5517_5546 = cljs.core.seq(self__.watching);var chunk__5518_5547 = null;var count__5519_5548 = (0);var i__5520_5549 = (0);while(true){
if((i__5520_5549 < count__5519_5548))
{var w_5550 = chunk__5518_5547.cljs$core$IIndexed$_nth$arity$2(null,i__5520_5549);if(cljs.core.contains_QMARK_(derefed,w_5550))
{} else
{cljs.core.remove_watch(w_5550,this$__$1);
}
{
var G__5551 = seq__5517_5546;
var G__5552 = chunk__5518_5547;
var G__5553 = count__5519_5548;
var G__5554 = (i__5520_5549 + (1));
seq__5517_5546 = G__5551;
chunk__5518_5547 = G__5552;
count__5519_5548 = G__5553;
i__5520_5549 = G__5554;
continue;
}
} else
{var temp__4126__auto___5555 = cljs.core.seq(seq__5517_5546);if(temp__4126__auto___5555)
{var seq__5517_5556__$1 = temp__4126__auto___5555;if(cljs.core.chunked_seq_QMARK_(seq__5517_5556__$1))
{var c__4316__auto___5557 = cljs.core.chunk_first(seq__5517_5556__$1);{
var G__5558 = cljs.core.chunk_rest(seq__5517_5556__$1);
var G__5559 = c__4316__auto___5557;
var G__5560 = cljs.core.count(c__4316__auto___5557);
var G__5561 = (0);
seq__5517_5546 = G__5558;
chunk__5518_5547 = G__5559;
count__5519_5548 = G__5560;
i__5520_5549 = G__5561;
continue;
}
} else
{var w_5562 = cljs.core.first(seq__5517_5556__$1);if(cljs.core.contains_QMARK_(derefed,w_5562))
{} else
{cljs.core.remove_watch(w_5562,this$__$1);
}
{
var G__5563 = cljs.core.next(seq__5517_5556__$1);
var G__5564 = null;
var G__5565 = (0);
var G__5566 = (0);
seq__5517_5546 = G__5563;
chunk__5518_5547 = G__5564;
count__5519_5548 = G__5565;
i__5520_5549 = G__5566;
continue;
}
}
} else
{}
}
break;
}
return self__.watching = derefed;
});
reagent.ratom.Reaction.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this$,writer,opts){var self__ = this;
var this$__$1 = this;cljs.core._write(writer,("#<Reaction "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.hash(this$__$1))+": "));
cljs.core.pr_writer(self__.state,writer,opts);
return cljs.core._write(writer,">");
});
reagent.ratom.Reaction.prototype.cljs$core$IHash$_hash$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;return goog.getUid(this$__$1);
});
reagent.ratom.Reaction.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (o,other){var self__ = this;
var o__$1 = this;return (o__$1 === other);
});
reagent.ratom.Reaction.prototype.reagent$ratom$IDisposable$ = true;
reagent.ratom.Reaction.prototype.reagent$ratom$IDisposable$dispose_BANG_$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;var seq__5521_5567 = cljs.core.seq(self__.watching);var chunk__5522_5568 = null;var count__5523_5569 = (0);var i__5524_5570 = (0);while(true){
if((i__5524_5570 < count__5523_5569))
{var w_5571 = chunk__5522_5568.cljs$core$IIndexed$_nth$arity$2(null,i__5524_5570);cljs.core.remove_watch(w_5571,this$__$1);
{
var G__5572 = seq__5521_5567;
var G__5573 = chunk__5522_5568;
var G__5574 = count__5523_5569;
var G__5575 = (i__5524_5570 + (1));
seq__5521_5567 = G__5572;
chunk__5522_5568 = G__5573;
count__5523_5569 = G__5574;
i__5524_5570 = G__5575;
continue;
}
} else
{var temp__4126__auto___5576 = cljs.core.seq(seq__5521_5567);if(temp__4126__auto___5576)
{var seq__5521_5577__$1 = temp__4126__auto___5576;if(cljs.core.chunked_seq_QMARK_(seq__5521_5577__$1))
{var c__4316__auto___5578 = cljs.core.chunk_first(seq__5521_5577__$1);{
var G__5579 = cljs.core.chunk_rest(seq__5521_5577__$1);
var G__5580 = c__4316__auto___5578;
var G__5581 = cljs.core.count(c__4316__auto___5578);
var G__5582 = (0);
seq__5521_5567 = G__5579;
chunk__5522_5568 = G__5580;
count__5523_5569 = G__5581;
i__5524_5570 = G__5582;
continue;
}
} else
{var w_5583 = cljs.core.first(seq__5521_5577__$1);cljs.core.remove_watch(w_5583,this$__$1);
{
var G__5584 = cljs.core.next(seq__5521_5577__$1);
var G__5585 = null;
var G__5586 = (0);
var G__5587 = (0);
seq__5521_5567 = G__5584;
chunk__5522_5568 = G__5585;
count__5523_5569 = G__5586;
i__5524_5570 = G__5587;
continue;
}
}
} else
{}
}
break;
}
self__.watching = cljs.core.PersistentHashSet.EMPTY;
self__.state = null;
self__.dirty_QMARK_ = true;
if(cljs.core.truth_(self__.active_QMARK_))
{if(cljs.core.truth_(reagent.ratom.debug))
{cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(reagent.ratom._running,cljs.core.dec);
} else
{}
self__.active_QMARK_ = false;
} else
{}
if(cljs.core.truth_(self__.on_dispose))
{return (self__.on_dispose.cljs$core$IFn$_invoke$arity$0 ? self__.on_dispose.cljs$core$IFn$_invoke$arity$0() : self__.on_dispose.call(null));
} else
{return null;
}
});
reagent.ratom.Reaction.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (a,new_value){var self__ = this;
var a__$1 = this;var old_value = self__.state;self__.state = new_value;
cljs.core._notify_watches(a__$1,old_value,new_value);
return new_value;
});
reagent.ratom.Reaction.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (a,f__$1){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f__$1.cljs$core$IFn$_invoke$arity$1 ? f__$1.cljs$core$IFn$_invoke$arity$1(self__.state) : f__$1.call(null,self__.state)));
});
reagent.ratom.Reaction.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (a,f__$1,x){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f__$1.cljs$core$IFn$_invoke$arity$2 ? f__$1.cljs$core$IFn$_invoke$arity$2(self__.state,x) : f__$1.call(null,self__.state,x)));
});
reagent.ratom.Reaction.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (a,f__$1,x,y){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,(f__$1.cljs$core$IFn$_invoke$arity$3 ? f__$1.cljs$core$IFn$_invoke$arity$3(self__.state,x,y) : f__$1.call(null,self__.state,x,y)));
});
reagent.ratom.Reaction.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (a,f__$1,x,y,more){var self__ = this;
var a__$1 = this;return cljs.core._reset_BANG_(a__$1,cljs.core.apply.cljs$core$IFn$_invoke$arity$5(f__$1,self__.state,x,y,more));
});
reagent.ratom.Reaction.prototype.reagent$ratom$IRunnable$ = true;
reagent.ratom.Reaction.prototype.reagent$ratom$IRunnable$run$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;var oldstate = self__.state;var res = reagent.ratom.capture_derefed(self__.f,this$__$1);var derefed = reagent.ratom.captured(this$__$1);if(cljs.core.not_EQ_.cljs$core$IFn$_invoke$arity$2(derefed,self__.watching))
{reagent.ratom._update_watching(this$__$1,derefed);
} else
{}
if(cljs.core.truth_(self__.active_QMARK_))
{} else
{if(cljs.core.truth_(reagent.ratom.debug))
{cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(reagent.ratom._running,cljs.core.inc);
} else
{}
self__.active_QMARK_ = true;
}
self__.dirty_QMARK_ = false;
self__.state = res;
reagent.ratom.call_watches(this$__$1,self__.watches,oldstate,self__.state);
return res;
});
reagent.ratom.Reaction.prototype.cljs$core$IWatchable$_notify_watches$arity$3 = (function (this$,oldval,newval){var self__ = this;
var this$__$1 = this;if(cljs.core.truth_(self__.on_set))
{(self__.on_set.cljs$core$IFn$_invoke$arity$2 ? self__.on_set.cljs$core$IFn$_invoke$arity$2(oldval,newval) : self__.on_set.call(null,oldval,newval));
} else
{}
return reagent.ratom.call_watches(this$__$1,self__.watches,oldval,newval);
});
reagent.ratom.Reaction.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,k,wf){var self__ = this;
var this$__$1 = this;return self__.watches = cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.watches,k,wf);
});
reagent.ratom.Reaction.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (this$,k){var self__ = this;
var this$__$1 = this;self__.watches = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.watches,k);
if(cljs.core.empty_QMARK_(self__.watches))
{return reagent.ratom.dispose_BANG_(this$__$1);
} else
{return null;
}
});
reagent.ratom.Reaction.prototype.cljs$core$IDeref$_deref$arity$1 = (function (this$){var self__ = this;
var this$__$1 = this;if(cljs.core.not((function (){var or__3560__auto__ = self__.auto_run;if(cljs.core.truth_(or__3560__auto__))
{return or__3560__auto__;
} else
{return reagent.ratom._STAR_ratom_context_STAR_;
}
})()))
{var x__5157__auto___5588 = new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [self__.auto_run,reagent.ratom._STAR_ratom_context_STAR_], null);if(!((console.log == null)))
{console.log((''+cljs.core.str.cljs$core$IFn$_invoke$arity$1(("dbg reagent.ratom:"+177+": [auto-run *ratom-context*]: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([x__5157__auto___5588], 0)))))));
} else
{}
} else
{}
if(cljs.core.truth_((function (){var or__3560__auto__ = self__.auto_run;if(cljs.core.truth_(or__3560__auto__))
{return or__3560__auto__;
} else
{return reagent.ratom._STAR_ratom_context_STAR_;
}
})()))
{} else
{throw (new Error(("Assert failed: Reaction derefed outside auto-running context\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.list(new cljs.core.Symbol(null,"or","or",1876275696,null),new cljs.core.Symbol(null,"auto-run","auto-run",-696035332,null),new cljs.core.Symbol(null,"*ratom-context*","*ratom-context*",-1557728360,null))], 0))))));
}
reagent.ratom.notify_deref_watcher_BANG_(this$__$1);
if(cljs.core.truth_(self__.dirty_QMARK_))
{return reagent.ratom.run(this$__$1);
} else
{return self__.state;
}
});
reagent.ratom.__GT_Reaction = (function __GT_Reaction(f,state,dirty_QMARK_,active_QMARK_,watching,watches,auto_run,on_set,on_dispose){return (new reagent.ratom.Reaction(f,state,dirty_QMARK_,active_QMARK_,watching,watches,auto_run,on_set,on_dispose));
});
/**
* @param {...*} var_args
*/
reagent.ratom.make_reaction = (function() { 
var make_reaction__delegate = function (f,p__5589){var map__5591 = p__5589;var map__5591__$1 = ((cljs.core.seq_QMARK_(map__5591))?cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,map__5591):map__5591);var derefed = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5591__$1,cljs.core.constant$keyword$47);var on_dispose = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5591__$1,cljs.core.constant$keyword$48);var on_set = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5591__$1,cljs.core.constant$keyword$49);var auto_run = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__5591__$1,cljs.core.constant$keyword$50);var runner = ((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(auto_run,true))?reagent.ratom.run:auto_run);var active = !((derefed == null));var dirty = !(active);var reaction = (new reagent.ratom.Reaction(f,null,dirty,active,null,cljs.core.PersistentArrayMap.EMPTY,runner,on_set,on_dispose));if((derefed == null))
{} else
{if(cljs.core.truth_(reagent.ratom.debug))
{cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(reagent.ratom._running,cljs.core.inc);
} else
{}
reaction.reagent$ratom$IComputedImpl$_update_watching$arity$2(null,derefed);
}
return reaction;
};
var make_reaction = function (f,var_args){
var p__5589 = null;if (arguments.length > 1) {
  p__5589 = cljs.core.array_seq(Array.prototype.slice.call(arguments, 1),0);} 
return make_reaction__delegate.call(this,f,p__5589);};
make_reaction.cljs$lang$maxFixedArity = 1;
make_reaction.cljs$lang$applyTo = (function (arglist__5592){
var f = cljs.core.first(arglist__5592);
var p__5589 = cljs.core.rest(arglist__5592);
return make_reaction__delegate(f,p__5589);
});
make_reaction.cljs$core$IFn$_invoke$arity$variadic = make_reaction__delegate;
return make_reaction;
})()
;
