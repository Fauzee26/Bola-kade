//import android.app.*
//import android.content.Context
//import android.graphics.Typeface
//import android.view.*
//import android.widget.*
//import org.jetbrains.anko.*
//import android.os.Bundle
//import fauzi.hilmy.bola_kade.R
//
//class DetailUI : AnkoComponent<Context> {
//    override fun createView(ui: AnkoContext<Context>): View {
//        return with(ui) {
//            linearLayout {
//                orientation = LinearLayout.VERTICAL
//                padding = dip(8)
//
//                textView {
//                    gravity = Gravity.CENTER
//                    id = R.id.txtDetailDate
//                    textColor = R.color.colorPrimary
//                    textSize = 18f
//                    typeface = Typeface.DEFAULT_BOLD
//                }.lparams(width = matchParent, height = wrapContent)
//                textView {
//                    gravity = Gravity.CENTER
//                    id = R.id.txtDetailTime
//                    textColor = R.color.colorAccentt
//                    textSize = 18f
//                    typeface = Typeface.DEFAULT_BOLD
//                }.lparams(width = matchParent, height = wrapContent)
//                linearLayout {
//                    orientation = LinearLayout.HORIZONTAL
//                    padding = dip(8)
//
//                    relativeLayout {
//                        linearLayout {
//                            id = R.id.txtHomeName
//                            orientation = LinearLayout.VERTICAL
//
//                            imageView {
//                                id = R.id.imgDetailHome
//                            }.lparams(width = dip(100), height = dip(100))
//                            textView {
//                                id = R.id.txtDetailHome
//                                textColor = R.color.colorPrimaryDark
//                                textSize = 19f
//                            }.lparams(width = wrapContent, height = wrapContent) {
//                                gravity = Gravity.CENTER_HORIZONTAL
//                            }
//                        }.lparams(width = wrapContent, height = wrapContent) {
//                            alignParentLeft()
//                            centerVertically()
//                            leftMargin = dip(10)
//                        }
//                        textView {
//                            gravity = Gravity.CENTER
//                            id = R.id.txtDetailScoreHome
//                            textSize = 40f
//                        }.lparams(width = wrapContent, height = wrapContent) {
//                            toRightOf(R.id.txtHomeName)
//                            toLeftOf(R.id.txtdetvs)
//                            centerVertically()
//                        }
//                        textView {
//                            gravity = Gravity.CENTER
//                            id = R.id.txtDetailScoreAway
//                            textSize = 40f
//                        }.lparams(width = wrapContent, height = wrapContent) {
//                            toRightOf(R.id.txtdetvs)
//                            toLeftOf(R.id.txtAwayName)
//                            centerVertically()
//                        }
//                        relativeLayout {
//                            id = R.id.txtdetvs
//
//                            textView("VS") {
//                                textSize = 20f
//                            }.lparams(width = wrapContent, height = wrapContent) {
//                                centerInParent()
//                            }
//                        }.lparams(width = wrapContent, height = wrapContent) {
//                            centerInParent()
//                        }
//                        linearLayout {
//                            id = R.id.txtAwayName
//                            orientation = LinearLayout.VERTICAL
//
//                            imageView {
//                                id = R.id.imgDetailAway
//                                src = R.color.colorPrimaryDark
//                            }.lparams(width = dip(100), height = dip(100))
//                            textView {
//                                id = R.id.txtDetailAway
//                                textColor = R.color.colorPrimaryDark
//                                textSize = 19f
//                            }.lparams(width = wrapContent, height = wrapContent) {
//                                gravity = Gravity.CENTER_HORIZONTAL
//                            }
//                        }.lparams(width = wrapContent, height = wrapContent) {
//                            alignParentRight()
//                            alignParentEnd()
//                            centerVertically()
//                            rightMargin = dip(10)
//                        }
//                    }.lparams(width = matchParent, height = dip(150))
//                }.lparams(width = matchParent, height = wrapContent)
//                relativeLayout {
//                    imageView {
//                        id = R.id.txtGoal
//                        src = R.drawable.ball
//                    }.lparams(width = dip(30), height = dip(30)) {
//                        centerInParent()
//                    }
//                    textView {
//                        id = R.id.txtDetailGoalHome
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toLeftOf(R.id.txtGoal)
//                    }
//                    textView {
//                        gravity = Gravity.RIGHT
//                        id = R.id.txtDetailGoalAway
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toRightOf(R.id.txtGoal)
//                    }
//                }.lparams(width = matchParent, height = wrapContent)
//                relativeLayout {
//                    imageView {
//                        id = R.id.txtYellow
//                        src = R.drawable.yellow_card
//                    }.lparams(width = dip(30), height = dip(30)) {
//                        centerInParent()
//                    }
//                    textView {
//                        id = R.id.txtDetailYellowHome
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toLeftOf(R.id.txtYellow)
//                    }
//                    textView {
//                        gravity = Gravity.RIGHT
//                        id = R.id.txtDetailYellowAway
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toRightOf(R.id.txtYellow)
//                    }
//                }.lparams(width = matchParent, height = wrapContent) {
//                    topMargin = dip(9)
//                }
//                relativeLayout {
//                    imageView {
//                        id = R.id.txtRed
//                        src = R.drawable.red_card
//                    }.lparams(width = dip(30), height = dip(30)) {
//                        centerInParent()
//                    }
//                    textView {
//                        id = R.id.txtDetailRedHome
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toLeftOf(R.id.txtRed)
//                    }
//                    textView {
//                        gravity = Gravity.RIGHT
//                        id = R.id.txtDetailRedAway
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toRightOf(R.id.txtRed)
//                    }
//                }.lparams(width = matchParent, height = wrapContent) {
//                    topMargin = dip(9)
//                }
//                relativeLayout {
//                    imageView {
//                        id = R.id.tttt
//                        src = 0xfff.opaque
//                    }.lparams(width = dip(30), height = dip(30)) {
//                        centerInParent()
//                    }
//                    button("Lineups") {
//                        backgroundResource = R.color.colorPrimary
//                        id = R.id.btnDetailLineupHome
//                        textColor = android.R.color.white
//                        textSize = 17f
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toLeftOf(R.id.tttt)
//                    }
//                    button("Lineups") {
//                        backgroundResource = R.color.colorPrimary
//                        id = R.id.btnDetailLineupAway
//                        textColor = android.R.color.white
//                        textSize = 17f
//                    }.lparams(width = matchParent, height = wrapContent) {
//                        toRightOf(R.id.tttt)
//                    }
//                }.lparams(width = matchParent, height = wrapContent) {
//                    topMargin = dip(20)
//                }
//            }
//        }
//    }
//}