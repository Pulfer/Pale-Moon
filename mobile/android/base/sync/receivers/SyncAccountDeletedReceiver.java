/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.goanna.sync.receivers;

import org.mozilla.goanna.background.common.log.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SyncAccountDeletedReceiver extends BroadcastReceiver {
  public static final String LOG_TAG = "SyncAccountDeletedReceiver";

  /**
   * This receiver can be killed as soon as it returns, but we have things to do
   * that can't be done on the main thread (network activity). Therefore we
   * start a service to do our clean up work for us, with Android doing the
   * heavy lifting for the service's lifecycle.
   * <p>
   * See <a href="http://developer.android.com/reference/android/content/BroadcastReceiver.html#ReceiverLifecycle">the Android documentation</a>
   * for details.
   */
  @Override
  public void onReceive(final Context context, Intent broadcastIntent) {
    Logger.debug(LOG_TAG, "Sync Account Deleted broadcast received.");

    Intent serviceIntent = new Intent(context, SyncAccountDeletedService.class);
    serviceIntent.putExtras(broadcastIntent);
    context.startService(serviceIntent);
  }
}
