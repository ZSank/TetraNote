package com.zsank.tetranote.model

import com.zsank.tetranote.data.Note

class TestNoteData {
	private fun noteData(): () -> Note {
		return {
			Note(null,"App architecture", "App architecture design is an important consideration for ensuring that your apps are robust, testable, and maintainable. Android provides a set of libraries and components to help you put together your app according to best practices.")
			Note(null,"Mobile app user experiences","A typical Android app contains multiple app components, including activities, fragments, services, content providers, and broadcast receivers. You declare most of these app components in your app manifest. The Android OS then uses this file to decide how to integrate your app into the device's overall user experience. Given that a typical Android app might contain multiple components and that users often interact with multiple apps in a short period of time, apps need to adapt to different kinds of user-driven workflows and tasks.\n" +
				"\n" +
				"Keep in mind that mobile devices are also resource-constrained, so at any time, the operating system might kill some app processes to make room for new ones.\n" +
				"\n" +
				"Given the conditions of this environment, it's possible for your app components to be launched individually and out-of-order, and the operating system or user can destroy them at any time. Because these events aren't under your control, you shouldn't store or keep in memory any application data or state in your app components, and your app components shouldn't depend on each other.")
		}
	}
}