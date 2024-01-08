import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http'; // Import provideHttpClient

// Add the provideHttpClient() function to the appConfig.providers array
appConfig.providers.push(provideHttpClient());

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));
