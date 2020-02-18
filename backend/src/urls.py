from django.urls import include, path
from rest_framework import routers
from src.quickstart import views
from rest_framework_simplejwt import views as jwt_views
from django.contrib import admin


urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('location.urls')),
    path('api/token/', jwt_views.TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', jwt_views.TokenRefreshView.as_view(), name='token_refresh'),

]