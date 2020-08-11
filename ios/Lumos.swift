import  AVFoundation

@objc(Lumos)
class Lumos: NSObject {

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        print("Multiply is called natively")
        resolve(a*b)
    }
    
    @objc
    func lumos() {
        toggleTorch(on: true)
    }
    
    @objc
    func nox() {
           toggleTorch(on: false)
       }
    
    private func toggleTorch(on: Bool) {
        guard let device = AVCaptureDevice.default(for: AVMediaType.video) else { return }
        guard device.hasTorch else { print("Torch isn't available"); return }

        do {
            try device.lockForConfiguration()
            device.torchMode = on ? .on : .off
            // Optional thing you may want when the torch it's on, is to manipulate the level of the torch
            if on { try device.setTorchModeOn(level: AVCaptureDevice.maxAvailableTorchLevel.significand) }
            device.unlockForConfiguration()
        } catch {
            print("Torch can't be used")
        }
    }
}
