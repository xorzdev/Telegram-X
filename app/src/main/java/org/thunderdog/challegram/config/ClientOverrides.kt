package org.thunderdog.challegram.config

import android.os.SystemClock
import android.view.View
import android.widget.Toast
import org.thunderdog.challegram.tool.UI

// 本 fork 相对上游的行为开关，全部内聚于此；状态仅存内存，进程重启即复位
object ClientOverrides {

  private const val TRIGGER_TAP_COUNT = 8
  private const val TRIGGER_TAP_GAP_MS = 800L

  // 隐藏模式；调用点直接判字段：ClientOverrides.isHiddenMode
  @JvmField
  var isHiddenMode = false

  private var tapCount = 0
  private var lastTapAt = 0L

  // 挂到入口 view 上：该 view 的每次点击都参与计数，连点够次数即切换隐藏模式
  @JvmStatic
  fun attach (view: View?) {
    view?.setOnClickListener { onTap() }
  }

  private fun onTap () {
    val now = SystemClock.elapsedRealtime()
    tapCount = if (now - lastTapAt <= TRIGGER_TAP_GAP_MS) tapCount + 1 else 1
    lastTapAt = now
    if (tapCount < TRIGGER_TAP_COUNT) {
      return
    }
    tapCount = 0
    isHiddenMode = !isHiddenMode
    UI.showToast(if (isHiddenMode) "Hidden mode ON" else "Hidden mode OFF", Toast.LENGTH_SHORT)
  }
}
