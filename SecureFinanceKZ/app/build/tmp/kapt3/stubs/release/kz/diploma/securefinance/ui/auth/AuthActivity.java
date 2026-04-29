package kz.diploma.securefinance.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0012\u0010\u0019\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u000bH\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lkz/diploma/securefinance/ui/auth/AuthActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnBiometric", "Lcom/google/android/material/button/MaterialButton;", "btnUnlock", "etPin", "Lcom/google/android/material/textfield/TextInputEditText;", "failedAttempts", "", "isLocked", "", "lockDelays", "", "lockTimer", "Landroid/os/CountDownTimer;", "progressBar", "Landroid/widget/ProgressBar;", "tilPin", "Lcom/google/android/material/textfield/TextInputLayout;", "tvError", "Landroid/widget/TextView;", "attemptBiometricUnlock", "", "attemptPinUnlock", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onWrongPin", "setLoading", "on", "setupKeypad", "showError", "msg", "", "startLockTimer", "ms", "", "app_release"})
public final class AuthActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.google.android.material.textfield.TextInputLayout tilPin;
    private com.google.android.material.textfield.TextInputEditText etPin;
    private com.google.android.material.button.MaterialButton btnUnlock;
    private com.google.android.material.button.MaterialButton btnBiometric;
    private android.widget.TextView tvError;
    private android.widget.ProgressBar progressBar;
    private int failedAttempts = 0;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer lockTimer;
    private boolean isLocked = false;
    @org.jetbrains.annotations.NotNull()
    private final long[] lockDelays = {30000L, 60000L, 120000L, 300000L, 600000L};
    
    public AuthActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupKeypad() {
    }
    
    private final void attemptPinUnlock() {
    }
    
    private final void attemptBiometricUnlock() {
    }
    
    private final void onWrongPin() {
    }
    
    private final void startLockTimer(long ms) {
    }
    
    private final void showError(java.lang.String msg) {
    }
    
    private final void setLoading(boolean on) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}