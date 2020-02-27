from django.urls import include, path
from rest_framework import routers
from src.quickstart import views
from rest_framework_simplejwt import views as jwt_views
from django.contrib import admin
from django.conf import settings
from django.conf.urls.static import static

from django.conf.urls import url
from rest_framework_swagger.views import get_swagger_view

schema_view = get_swagger_view(title='Location Service API')

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('location.urls')),
    path('api/token/', jwt_views.TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', jwt_views.TokenRefreshView.as_view(), name='token_refresh'),
    path('doc/', schema_view)
]

if settings.DEBUG:
  urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)